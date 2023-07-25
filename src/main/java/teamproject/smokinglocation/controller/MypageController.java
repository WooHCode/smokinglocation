package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.userEnitiy.Member;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public String myPage(@PathVariable Long id, Model model) {

        Member member = memberService.findById(id);
        model.addAttribute("email", member.getMemberId());
        model.addAttribute("password", member.getPassword());
        model.addAttribute("inquiries", member.getInquiries());
        return "mypage/mypage";
    }

    @PostMapping("/{id}")
    public String pwChange(@PathVariable Long id, @ModelAttribute("password") String password, RedirectAttributes redirectAttributes) {
        Member member = memberService.findById(id);
        memberService.updateMemberPassword(member, password);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/mypage/{id}";
    }
}
