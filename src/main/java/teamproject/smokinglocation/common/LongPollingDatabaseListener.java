package teamproject.smokinglocation.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.service.NotificationService;

@Component
@RequiredArgsConstructor
@Slf4j
public class LongPollingDatabaseListener {

    @Value("${cluster.nodeid}")
    private String nodeId;

    private final NotificationService notificationService;

    @Scheduled(fixedRate = 3600000)// 1시간 간격으로 확인 
    public void checkNotifications() {
    	log.info("LongPollingDatabaseListener - checkNotifications");
        if (notificationService.containsNotifications(nodeId)) {
        	notificationService.outgoingNotification(notificationService.getAndRemoveNotifications(nodeId));
        }
    }

}
