package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.service.MailService;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.service.NotificationService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
    private final NotificationService notificationsService;
    
    private final MailService mailService;
    
    private final MemberService memberService;
    
    @Value("${cluster.nodenames}")
    private String[] nodeNames;
    
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

    @GetMapping("/main-board")
    public String getBoard(@RequestParam("rf") String rf, Model model) {
        Long memberId = memberService.getMemberIdByRefreshToken(rf);
        Member member = memberService.findById(memberId);
        NewInquiryDto dto = new NewInquiryDto();
        dto.setMemberName(member.getMemberName());
        dto.setMemberId(member.getMemberId());
        model.addAttribute("dto", dto);
        return "body/board2";
    }

    @ResponseBody
    @PostMapping("/board")
    public void saveInquiry(@ModelAttribute NewInquiryDto dto, RedirectAttributes redirectAttributes) {
        String content = dto.getContent();
        String memberId = dto.getMemberId();
        log.info("==============content : {}", content);
        log.info("==============memberId : {}", memberId);

        Member member = memberService.findByEmail(memberId);
        inquiryService.createInquiry(member, content);

    }

    @GetMapping("/main-ask-complete")
    public String getAskComplete(@RequestParam("refreshToken") String refreshToken, @RequestParam("content") String content) throws Exception {
    	long id = memberService.getMemberIdByRefreshToken(refreshToken);
    	
        /* Notifications INSERT 오우석 추가 */
        log.info("BoardController - Notifications INSERT START ");
        for (final String node : nodeNames) { 
        	// nodeNames 서버 이중화일때 사용하는건지 ? 추후 확인 필요 ....
            Notifications notification = new Notifications();
            notification.setUserId(id);
            notification.setTimestamp(new Date());
            notification.setNodeId(node);
            notification.setPayload(" Answer For ::: " +  id);
            notificationsService.save(notification);
        }
        notificationsService.flush(); // force the changes to the DB
    	log.info("BoardController - Notifications INSERT END ");
    	
    	/* 메일 전송  오우석 추가 */
    	mailService.sendSimpleMessage(id,content);
    	
        return "body/ask-complete";
    }
}
