package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import teamproject.smokinglocation.common.LongPollingSession;
import teamproject.smokinglocation.service.MemberService;
import teamproject.smokinglocation.service.NotificationService;


@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final MemberService memberService;
    
    private final NotificationService notificationService;
    
    private long userId;
    
    @RequestMapping("/polling-register")
    @ResponseBody
    public DeferredResult<String> registerClient(@RequestParam("refreshToken") String refreshToken) {
    	userId = memberService.getMemberIdByRefreshToken(refreshToken);
        log.info("NotificationController - registerClient START for " + userId);
        
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        // Add paused http requests to event queue
        notificationService.getPollingQueue().add(new LongPollingSession(userId, deferredResult));
        log.info( "size:::::::::::::::::: " +notificationService.getPollingQueue().size());
        log.info("NotificationController - registerClient END for " + userId);
        return deferredResult;
    }

    
}

