package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import teamproject.smokinglocation.common.TokenInfo;
import teamproject.smokinglocation.dto.memberDto.MemberRegisterRequestDto;
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
     * 비밀번호 찾기
     * MemberLoginRequestDto(email, name)
     * @param dto
     * @return
     */
    @PostMapping("/findPw")
    public ResponseEntity<?> findPw(@RequestBody MemberRegisterRequestDto dto) {
        log.info("===========findPw process start===========");
        String memberId = dto.getEmail(); 	// 이메일
        String memberName  = dto.getName();	// 이름
        String message = "";				// 결과메세지
        Member count = memberService.findPw(memberId, memberName);
        if("1".equals(count)) {
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
     * @return
     */
    @GetMapping("/admin")
    public String adminPage() {
        return "mypage/adminmypage";
    }
}
