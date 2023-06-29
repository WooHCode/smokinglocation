package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.dto.memberDto.MemberLoginRequestDto;
import teamproject.smokinglocation.dto.memberDto.MemberRegisterRequestDto;
import teamproject.smokinglocation.service.MemberService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final MemberService memberService;

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

    @PostMapping("/register-member")
    public String registerMember(@RequestBody MemberRegisterRequestDto dto) {
        String name = dto.getName();
        String password = dto.getPassword();
        String email = dto.getEmail();
        boolean registerSuccess = memberService.register(email, name, password);
        if (registerSuccess) {
            memberService.deleteTotalMemberIdCache();
            memberService.findAllMemberId();
        }
        return "success";
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto dto) {
        String memberId = dto.getMemberId();
        String password = dto.getPassword();

        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
