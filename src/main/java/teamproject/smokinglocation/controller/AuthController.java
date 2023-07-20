package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.dto.memberDto.MemberLoginRequestDto;
import teamproject.smokinglocation.dto.memberDto.MemberRegisterRequestDto;
import teamproject.smokinglocation.dto.tokenDto.MemberLogoutRequestDto;
import teamproject.smokinglocation.service.MemberService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    /**
     * 프론트에서 회원가입 시 이메일 중복체크 API
     * @param email
     * @return
     */
    @GetMapping("/check-email")
    public String checkEmail(@RequestParam String email) {
        if (email.length() < 9) {
            return "이메일 형식으로 입력해주세요.";
        }
        List<String> allMemberId = memberService.findAllMemberId();
        for (String memberId : allMemberId) {
            log.info(memberId);
            if (memberId.equals(email)) {
                return "이미 가입된 아이디 입니다.";
            }
        }
        return "사용하실 수 있는 아이디 입니다.";
    }

    /**
     * 회원가입
     * param:  email, name, password
     * @return
     */
    @PostMapping("/register-member")
    public String registerMember(@RequestBody MemberRegisterRequestDto dto) {
        String name = dto.getName();
        String password = dto.getPassword();
        String email = dto.getEmail();
        if (!email.contains("@")) {
            return "아이디를 이메일 형식으로 작성해주세요.";
        }
        log.info("register member: {}, {}, {}",name, password, email);
        boolean registerSuccess = memberService.register(email, name, password);
        if (registerSuccess) {
            memberService.deleteTotalMemberIdCache();
            memberService.findAllMemberId();
        }
        return "success";
    }

    /**
     * 로그인 API
     * accessToken, refreshToken 발급
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto dto) {
        log.info("===========login process start===========");
        String memberId = dto.getEmail();
        String password = dto.getPassword();
        log.info("===========login success==============");
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return new ResponseEntity<>(tokenInfo, HttpStatus.OK);
    }

    /**
     * 로그아웃
     * 토큰 삭제
     * @param dto
     * @return
     */
    @PostMapping("/loggout")
    public ResponseEntity<?> logout(@RequestBody MemberLogoutRequestDto dto) {
        log.info("=========member logout process=============");
        String accessToken = dto.getAccessToken();
        String memberName = memberService.logout(accessToken);
        log.info("member {} is logged out",memberName);
        return new ResponseEntity<>(memberName, HttpStatus.OK);
    }
}
