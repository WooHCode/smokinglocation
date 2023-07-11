package teamproject.smokinglocation.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.config.JwtProvider;
import teamproject.smokinglocation.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = resolveAccessToken((HttpServletRequest) request);

        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (jwtProvider.validateExpiredToken(token)) {
            log.info("reissue token");
            TokenInfo reissueToken = memberService.reissue(token);
            Authentication authentication = jwtProvider.getAuthentication(reissueToken.getAccessToken());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("X-Access-Token", reissueToken.getAccessToken());
            httpResponse.setHeader("X-Refresh-Token", reissueToken.getRefreshToken());
        }
        chain.doFilter(request, response);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
