package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.smokinglocation.dto.memberDto.SaveSpotDto;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.userEnitiy.Member;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/auth")
    public Long getMemberId(@RequestParam("refreshToken") String refreshToken) {
        log.info("===========toMypage : refreshToken= {} ========", refreshToken);
        return memberService.getMemberIdByRefreshToken(refreshToken);
    }

    @GetMapping("/{id}")
    public String myPage(@PathVariable Long id, Model model) {

        Member member = memberService.findById(id);
        model.addAttribute("email", member.getMemberId());
        model.addAttribute("password", member.getPassword());
        model.addAttribute("savedSpot", member.getSavedSpotList());
        model.addAttribute("inquiries", member.getInquiries());
        return "mypage/mypage";
    }

    @PostMapping("/{id}")
    public String pwChange(@PathVariable Long id, @ModelAttribute("password") String password, RedirectAttributes redirectAttributes) {
        Member member = memberService.findById(id);
        memberService.updateMemberPassword(member, password);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/member/{id}";
    }

    @ResponseBody
    @PostMapping("/savespot")
    public String saveSpot(@RequestBody SaveSpotDto dto) {
        log.info("===========saveSpot 진입===========");
        log.info("dto.getRefreshToken : {}", dto.getRefreshToken());
        log.info("dto.getFindLng : {}", dto.getFindLng());
        log.info("dto.getFindLat : {}", dto.getFindLat());
        log.info("dto.getFindLoc : {}", dto.getFindLoc());
        memberService.createSavedSpot(dto);
        return "ok";
    }

    /**
     * 관리자 페이지 확인을 위한 테스트 메서드
     * @return
     */
    @GetMapping("/admin")
    public String adminPage() {
        return "mypage/adminmypage";
    }
}
