package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import teamproject.smokinglocation.common.LongPollingEventSimulator;
import teamproject.smokinglocation.common.LongPollingSession;
import teamproject.smokinglocation.dto.MailDto;
import teamproject.smokinglocation.service.MailService;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.service.NotificationService;


@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final LongPollingEventSimulator simulator;
    
    private final MemberService memberService;
    
    private final NotificationService notificationService;
    
    private final MailService mailService;
    
    private long userId;
    
    @RequestMapping("/register")
    @ResponseBody
    public DeferredResult<String> registerClient(@RequestParam("refreshToken") String refreshToken) {
    	userId = memberService.getMemberIdByRefreshToken(refreshToken);
        log.info("NotificationController - registerClient START for " + userId);
        
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        // Add paused http requests to event queue
        notificationService.getPollingQueue().add(new LongPollingSession(userId, deferredResult));
        log.info("NotificationController - registerClient END for " + userId);
        return deferredResult;
    }

    @RequestMapping("/simulate")
    @ResponseBody
    public String simulateEvent(@RequestParam("refreshToken") String refreshToken) {
        log.info("NotificationController - simulateEvent START for " + userId);
    	simulator.simulateIncomingNotification(userId);
    	
    	MailDto mailDto = null;
    	mailService.sendSimpleMessage(mailDto);
    	
    	log.info("NotificationController - simulateEvent END for "+ userId);
        return "NotificationController - simulateEvent END for  " + userId;
    }
    
}

