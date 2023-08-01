package teamproject.smokinglocation.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.service.NotificationService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
@Slf4j
public class LongPollingDatabaseListener {

    @Value("${cluster.nodeid}")
    private String nodeId;

    private final NotificationService dbService;

    @Autowired
    LongPollingEventSimulator simulator;

    @Scheduled(fixedRate = 5000)
    public void checkNotifications() {
    	log.info("리스너 :::: checkNotifications: " + nodeId);
        if (dbService.containsNotifications(nodeId)) {
        	log.info("dbService.containsNotifications(nodeId) : " + dbService.containsNotifications(nodeId));
        	
            simulator.simulateOutgoingNotification(dbService.getAndRemoveNotifications(nodeId));
        }
    }

}
