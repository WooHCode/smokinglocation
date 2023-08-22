package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.dto.inquiryDto.InquiryDto;
import teamproject.smokinglocation.inquiryentity.Inquiry;
import teamproject.smokinglocation.service.InquiryService;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

//    /**
//     * 문의 작성 폼 입장 화면
//     */
//    @GetMapping("/write/enter")
//    public String boardPopup() {
//    }
//

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
    @GetMapping("/get-one?inquiryId={id}")
    public String getOneInquiry(@PathVariable Long id, Model model) {
        log.info("=======================");
        Inquiry inquiry = inquiryService.findOne(id);
        model.addAttribute("inq", inquiry);
        log.info("{}============", inquiry.getContent());
        return "testPage/findOne";
    }


    /**
     * 답변 작성
     */
    @ResponseBody
    @GetMapping("/add-reply")
    public String addReply() {
        inquiryService.addReply(1L, "reply");
        return "ok";
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
