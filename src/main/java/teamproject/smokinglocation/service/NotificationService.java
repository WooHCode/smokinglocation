package teamproject.smokinglocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.common.LongPollingEventSimulator;
import teamproject.smokinglocation.common.LongPollingSession;
import teamproject.smokinglocation.config.JwtProvider;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.repository.MemberRepository;
import teamproject.smokinglocation.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@EnableCaching
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
	private final BlockingQueue<LongPollingSession> longPollingQueue = new ArrayBlockingQueue<LongPollingSession>(10000);
	
    private final NotificationRepository notificationRepository;

    private Date getTimestamp() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -10);
        return cal.getTime();
    }
    
    /*
     * 작업 완료 후 나가는 부분 
     * */
    public void outgoingNotification(final List<Notifications> notifications) {
    	log.info("LongPollingEventSimulator - simulateOutgoingNotification START ");
    	//for (final Notifications Notification : mergeNotificationPayloads(notifications)) {
    	for (Notifications Notification : notifications) {
    			getPollingQueue().stream()
            		.filter(e -> e.getUserId() == Notification.getUserId())
                    .forEach((final LongPollingSession lps) -> {
                        try {
                            lps.getDeferredResult().setResult(Notification.getPayload());
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    });
        }
        getPollingQueue().removeIf(e -> e.getDeferredResult().isSetOrExpired());
        log.info("LongPollingEventSimulator - simulateOutgoingNotification END ");
    }
    
    /*
     * Notification 정보 가져오기
     * */
    public List<Notifications> getNotifications(final String nodeId) {
        return notificationRepository.getNotifications(nodeId, getTimestamp());
    }

    public List<Notifications> getAndRemoveNotifications(final String nodeId) {
    	log.info("NotificationService - getAndRemoveNotifications START ");
        final List<Notifications> notifications = getNotifications(nodeId);

        // Create a copy of the list before we delete the database entities
        // DATABASE row 삭제하기 전 notifications 복사  
        final List<Notifications> clonedNotifications = new ArrayList<Notifications>(notifications);

        // delete the database entities so we don't repeat notification sending
        // notification sending 을 반복하지 않기위해 DATABASE row 삭제
        notifications.stream().forEach(node -> notificationRepository.delete(node));
        flush();

        // return the copied list
    	log.info("NotificationService - getAndRemoveNotifications END ");
        return clonedNotifications;
    }

    public boolean containsNotifications(final String nodeId) {
        return !notificationRepository.getNotifications(nodeId, getTimestamp()).isEmpty();
    }

    public Notifications save(final Notifications node) {
        return notificationRepository.save(node);
    }

    public void flush() {
    	notificationRepository.flush();
    }
    
    public BlockingQueue<LongPollingSession> getPollingQueue() {
        return longPollingQueue;
    }

}
