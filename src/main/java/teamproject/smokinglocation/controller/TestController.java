package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.dto.memberDto.MemberLoginRequestDto;
import teamproject.smokinglocation.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final MemberService memberService;

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
