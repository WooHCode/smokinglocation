package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.smokinglocation.chat.ChatRoomRepository;
import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.dto.memberDto.MemberRegisterRequestDto;
import teamproject.smokinglocation.dto.memberDto.MemberUpdateDto;
import teamproject.smokinglocation.dto.memberDto.SaveSpotDto;
import teamproject.smokinglocation.inquiryentity.Inquiry;
import teamproject.smokinglocation.service.InquiryService;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.userEnitiy.Member;
import teamproject.smokinglocation.userEnitiy.SavedSpot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final InquiryService inquiryService;
    private final ChatRoomRepository chatRoomRepository;

    @ResponseBody
    @GetMapping("/auth")
    public Long getMemberId(@RequestParam("refreshToken") String refreshToken) {
        log.info("===========toMypage : refreshToken= {} ========", refreshToken);
        return memberService.getMemberIdByRefreshToken(refreshToken);
    }

    @GetMapping("/{id}")
    public String redirectTomyPage(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("member_pk", id);
        return "redirect:/member/mypage";
    }

    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("member_pk");

        Member member = memberService.findById(id);
        model.addAttribute("memberName", member.getMemberName());
        model.addAttribute("email", member.getMemberId());
        model.addAttribute("password", member.getPassword());
        model.addAttribute("savedSpot", member.getSavedSpotList());
        List<Inquiry> inquiries = inquiryService.findAllByMemberId(member.getId());
        model.addAttribute("inquiries", inquiries);
        return "mypage/mypage";
    }

    /*@PostMapping("/{id}")
    public String pwChange(@PathVariable Long id, @ModelAttribute("password") String password, RedirectAttributes redirectAttributes) {
        Member member = memberService.findById(id);
        memberService.updateMemberPassword(member, password);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/member/{id}";
    }*/

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
     * 비밀번호 찾기
     * MemberLoginRequestDto(email, name)
     *
     * @param dto
     * @return
     */
    @PostMapping("/findPw")
    public ResponseEntity<?> findPw(@RequestBody MemberRegisterRequestDto dto) {
        log.info("===========findPw process start===========");
        String memberId = dto.getEmail();    // 이메일
        String memberName = dto.getName();    // 이름
        String message = "";                // 결과메세지
        Member count = memberService.findPw(memberId, memberName);
        if ("1".equals(count)) {
            // 비밀번호 찾기 성공
            message = "Success";
        } else {
            // 비밀번호 찾기 실패
            message = "fail";
        }
        log.info("===========findPw success==============");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 관리자 페이지 확인을 위한 테스트 메서드
     *
     * @return
     */
    @GetMapping("/admin")
    public String adminPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("member_pk");

        Member member = memberService.findById(id);
        model.addAttribute("memberName", member.getMemberName());
        model.addAttribute("inquiries", inquiryService.findAll());
        model.addAttribute("chatRooms", chatRoomRepository.findAll());
        return "mypage/adminmypage";
    }

    @GetMapping("/mypage/info-update")
    public String getUpdatePage(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("member_pk");
        Member member = memberService.findById(memberId);
        MemberUpdateDto dto = new MemberUpdateDto();
        dto.setMemberId(member.getMemberId());
        dto.setMemberName(member.getMemberName());
        dto.setPassword(member.getPassword());
        dto.setProvider(member.getProvider());
        model.addAttribute("member", dto);
        return "mypage/updateInfo";
    }

    @PostMapping("/mypage/info-update")
    public String updateInfo(@ModelAttribute("member") MemberUpdateDto dto) {
        log.info("memberEmail : {}", dto.getMemberId());
        log.info("memberName : {}", dto.getMemberName());
        log.info("password : {}", dto.getPassword());
        memberService.updateInfo(dto.getMemberId(), dto.getMemberName(), dto.getPassword());
        return "redirect:/member/mypage";
    }
}
