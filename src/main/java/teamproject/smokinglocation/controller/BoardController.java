package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.smokinglocation.dto.inquiryDto.NewInquiryDto;
import teamproject.smokinglocation.service.InquiryService;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.userEnitiy.Member;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final MemberService memberService;
    private final InquiryService inquiryService;

    /**
     * 화면 반환 API
     *
     * @return
     */
    @GetMapping("/customerService")
    public String getCustomerService() {
        return "body/customerService";
    }

    @GetMapping("/board")
    public String getBoard(@RequestParam("rf") String rf, Model model) {
        Long memberId = memberService.getMemberIdByRefreshToken(rf);
        Member member = memberService.findById(memberId);
        NewInquiryDto dto = new NewInquiryDto();
        dto.setMemberName(member.getMemberName());
        dto.setMemberId(member.getMemberId());
        model.addAttribute("dto", dto);
        return "body/board";
    }

    @PostMapping("/board")
    public String saveInquiry(@ModelAttribute NewInquiryDto dto, RedirectAttributes redirectAttributes) {
        String content = dto.getContent();
        String memberId = dto.getMemberId();
        log.info("==============content : {}", content);
        log.info("==============memberId : {}", memberId);

        Member member = memberService.findByEmail(memberId);
        inquiryService.createInquiry(member, content);

        redirectAttributes.addAttribute("id", member.getId());
        return "redirect:/member/{id}";
    }

    @GetMapping("/ask-complete")
    public String getAskComplete() {
        return "body/ask-complete";
    }
}
