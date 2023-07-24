package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.service.MemberService;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    @GetMapping("/{memberId}")
    public String myPage(@PathVariable Long memberId) {
        return "mypage/mypage";
    }
}
