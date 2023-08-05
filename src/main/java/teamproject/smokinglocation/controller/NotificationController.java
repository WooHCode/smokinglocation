package teamproject.smokinglocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import teamproject.smokinglocation.common.LongPollingEventSimulator;
import teamproject.smokinglocation.common.LongPollingSession;
import teamproject.smokinglocation.repository.NotificationRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    @Autowired
    private NotificationRepository dao;

    @Autowired
    LongPollingEventSimulator simulator;

    @RequestMapping("/register")
    @ResponseBody
    public DeferredResult<String> registerClient(@PathVariable("dossierId") final long dossierId) {
        
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        // Add paused http requests to event queue
        simulator.getPollingQueue().add(new LongPollingSession( dossierId, deferredResult));
        log.info(String.valueOf(deferredResult));
        return deferredResult;
    }

    @RequestMapping("/simulate/{dossierId}")
    @ResponseBody
    public String simulateEvent(@PathVariable("dossierId") final long dossierId) {
        log.info("Simulating event for dossier id: " + dossierId);
        simulator.simulateIncomingNotification(dossierId);
        return "Simulating event for dossier Id: " + dossierId;
    }
}

