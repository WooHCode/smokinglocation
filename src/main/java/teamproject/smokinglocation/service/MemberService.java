package teamproject.smokinglocation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.config.JwtProvider;
import teamproject.smokinglocation.repository.MemberRepository;
import teamproject.smokinglocation.userEnitiy.Member;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@EnableCaching
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final CacheManager cacheManager;

    @Transactional
    @Cacheable("totalMemberId")
    public List<String> findAllMemberId() {
        return memberRepository.findMemberId();
    }
    @CacheEvict("totalMemberId")
    public void deleteTotalMemberIdCache() {
        Cache cache = cacheManager.getCache("totalMemberId");
        if (cache != null) {
            log.info("=============DELETE CACHE DATA ({})===============",cache);
            cache.clear();
        }
    }

    @Transactional
    public boolean register(String memberId, String memberName, String password) {
        List<String> roles = Collections.singletonList("USER"); // 사용자 역할 설정
        Member member = new Member();
        member.toEntity(memberId,memberName,password,roles);
        memberRepository.save(member);
        return true;
    }

    @Transactional
    public TokenInfo login(String memberId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId,password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtProvider.generateToken(authentication);

        return tokenInfo;
    }
}
