package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.smokinglocation.dto.inquiryDto.InquiryDto;
import teamproject.smokinglocation.inquiryentity.Inquiry;
import teamproject.smokinglocation.service.InquiryService;
import teamproject.smokinglocation.service.MemberService;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;
    private final MemberService memberService;

    /**
     * 문의 작성 폼 입장 화면
     */
    @GetMapping("/write/enter")
    public String boardPopup() {
        return "inquiry/inquiry";
    }


    /**
     * 문의 작성 완료
     */
    @PostMapping("/save")
    @ResponseBody
    public String boardSave(@ModelAttribute InquiryDto dto) {
        Inquiry inquiry = inquiryService.saveInquiry(dto);
        return inquiry.getTitle() + inquiry.getTitle();
    }

    /**
     * 전체 문의 조회
     */
    @ResponseBody
    @GetMapping("/get-all-inquiries")
    public String getAllInquiries() {
        List<Inquiry> all = inquiryService.findAll();
        return all.toString();
    }


    /**
     * 문의 상세 조회
     */
    @GetMapping("/getone")
    public String getOneInquiry(@RequestParam Long inquiryId, Model model) {
        Inquiry inquiry = inquiryService.findOne(inquiryId);
        InquiryDto inquiryDto = inquiryService.entityToDto(inquiry);
        model.addAttribute("inquiry", inquiryDto);
        model.addAttribute("member", inquiry.getMember());
        return "inquiry/inquiry";
    }

    /**
     * 문의 글 수정
     */
    @PostMapping("/getone")
    public String editInquiry(@RequestParam Long inquiryId, @ModelAttribute InquiryDto inquiry, RedirectAttributes redirectAttributes) {
        inquiryService.update(inquiryId, inquiry);
        Long memberId = inquiryService.findOne(inquiryId).getMember().getId();
        redirectAttributes.addAttribute("id", memberId);
        return "redirect:/member/{id}";
    }



    /**
     * 문의 상세 조회 ADMIN
     */
    @GetMapping("/getone/admin")
    public String getOneAdmin(@RequestParam Long inquiryId, Model model) {
        Inquiry inquiry = inquiryService.findOne(inquiryId);
        InquiryDto inquiryDto = inquiryService.entityToDto(inquiry);
        model.addAttribute("inquiry", inquiryDto);
        model.addAttribute("member", inquiry.getMember());
        return "inquiry/inquiryAdmin";
    }


    /**
     * 답변 작성
     */
    @PostMapping("/getone/admin")
    public String addReply(@ModelAttribute InquiryDto inquiryDto, @RequestParam Long inquiryId) {
        inquiryService.addReply(inquiryId, inquiryDto.getReply());
        return "redirect:/member/admin";
    }

    /**
     * 문의 삭제
     */
    @ResponseBody
    @GetMapping("/delete")
    public String deleteInquiry(@RequestParam Long id) {
        inquiryService.deleteInquiry(id);
        return "deleted";
    }

    //test
//    @GetMapping("/test")
//    public String test(Model model) {
//        model.addAttribute("dto", new InquiryDto());
//        return "testPage/inquiry";
//    }
//
//    @ResponseBody
//    @PostMapping("/test")
//    public String test(@ModelAttribute InquiryDto dto) {
//        return dto.getTitle() + dto.getContent();
//    }


}
