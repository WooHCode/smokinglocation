package teamproject.smokinglocation.service;

import java.util.Collections;
import java.util.List;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.config.JwtProvider;
import teamproject.smokinglocation.dto.memberDto.SaveSpotDto;
import teamproject.smokinglocation.repository.MemberRepository;
import teamproject.smokinglocation.repository.MemberRolesRepository;
import teamproject.smokinglocation.repository.SpotRepository;
import teamproject.smokinglocation.userEnitiy.Member;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

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
    private final SpotRepository spotRepository;
    private final MemberRolesRepository memberRolesRepository;

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
        Authentication authentication = getAuthentication(memberId, password);

        TokenInfo tokenInfo = jwtProvider.generateToken(authentication);
        memberRepository.updateRefreshToken(tokenInfo.getRefreshToken(), memberId);

        return tokenInfo;
    }

    @Transactional
    public TokenInfo reissue(String accessToken) {
        String memberId = jwtProvider.getMemberId(accessToken);
        String refreshToken = memberRepository.findRefreshToken(memberId);
        String password = memberRepository.findPasswordByMemberId(memberId);

        if (refreshToken != null && !refreshToken.isEmpty()) {
            Authentication authenticated = getAuthentication(memberId, password);
            TokenInfo tokenInfo = jwtProvider.generateToken(authenticated);
            String regenRefreshToken = tokenInfo.getRefreshToken();
            log.info("new refreshToken: {}", regenRefreshToken);
            memberRepository.updateRefreshToken(regenRefreshToken,memberId);
            return tokenInfo;
        }
        return null;
    }

    @Transactional
    public String logout(String accessToken) {
        String memberId = jwtProvider.getMemberId(accessToken);
        memberRepository.updateRefreshToken(null, memberId);
        String memberName = memberRepository.findMemberNameByMemberId(memberId);
        return memberName;
    }

    @Transactional
    public Long getMemberIdByRefreshToken(String refreshToken) {
        return memberRepository.findMemberIdByRefreshToken(refreshToken);
    }

    @Transactional
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Transactional
    public void updateMemberPassword(Member member, String password) {
        member.updatePassword(password);
    }

    private Authentication getAuthentication(String memberId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);
        Authentication authenticated = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return authenticated;
    }


    @Transactional
    public SavedSpot createSavedSpot(SaveSpotDto dto) {
        log.info("===============MemberService : createSaveSpot() ==============");
        String refreshToken = dto.getRefreshToken();
        log.info("refreshToken : {}",refreshToken);
        Long memberId = getMemberIdByRefreshToken(refreshToken);
        log.info("memberId : {}", memberId);
        Member member = findById(memberId);
        SavedSpot savedSpot = SavedSpot.builder()
                .lng(dto.getFindLng())
                .lat(dto.getFindLat())
                .loc(dto.getFindLoc())
                .member(member)
                .build();
        spotRepository.save(savedSpot);
        return savedSpot;
    }
    
    @Transactional
    public Member findPw(String memberId, String memberName) {
        return memberRepository.findByMemberIdAndMemberName(memberId, memberName);
    }

    public Member findByEmail(String memberId) {
        return memberRepository.findMemberEntityByEmail(memberId);
    }

    // 로그인 권한 정보 조회
    @Transactional
    public String getLoginRole(String memberId) {
    	Long id = memberRepository.findById(memberId);				// ID 조회
    	String roles = memberRolesRepository.findRolesByMemberId(id);	// 로그인 권한 조회
        return roles;
    }

    @Transactional
    public void updateInfo(String memberId, String memberName, String password) {
        Member member = memberRepository.findMemberEntityByEmail(memberId);
        if (password.isEmpty()) {
            member.updateInfo(memberName,member.getPassword());
        } else{
            member.updateInfo(memberName, password);
        }
    }
}
