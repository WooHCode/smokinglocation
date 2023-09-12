package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.service.MailService;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.service.NotificationService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	
    private final NotificationService notificationsService;
    
    private final MailService mailService;
    
    private final MemberService memberService;
    
    @Value("${cluster.nodenames}")
    private String[] nodeNames;
    
    /**
     * 화면 반환 API
     * @return
     */
    @GetMapping("/customerService")
    public String getCustomerService() {
        return "body/customerService";
    }

    @GetMapping("/board")
    public String getBoard() {
        return "body/board";
    }

    @GetMapping("/ask-complete")
    public String getAskComplete(@RequestParam("refreshToken") String refreshToken) {
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
    	//mailService.sendSimpleMessage(id);
    	
        return "body/ask-complete";
    }
}
