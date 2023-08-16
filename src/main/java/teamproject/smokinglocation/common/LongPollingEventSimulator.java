package teamproject.smokinglocation.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.controller.DirectionController;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.service.NotificationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Long Polling 테스트용 
 * 오우석 
 * 23.07.29
 */
@Component
@Slf4j
public class LongPollingEventSimulator {
    private final BlockingQueue<LongPollingSession> longPollingQueue = new ArrayBlockingQueue<LongPollingSession>(100);

    @Autowired
    private NotificationService dbService;


    @Autowired
    private MessagePayloadUtil messageUtil;

    @Value("${cluster.nodenames}")
    private String[] nodeNames;

    private String createMergedMapKey(final Long dossierId, final String nodeId) {
        return String.format("%d-%s", dossierId, nodeId);
    }

    private List<Notifications> mergeNotificationPayloads(final List<Notifications> notifications) {
     	log.info("mergeNotificationPayloads: ");
        final Map<String, Notifications> mergedNotifications = new ConcurrentHashMap<String, Notifications>();
        notifications.stream().forEach(Notification -> {
            final String key = createMergedMapKey(Notification.getDossierId(), Notification.getNodeId());
            if (mergedNotifications.containsKey(key)) {
                final Notifications node = mergedNotifications.get(key);
                node.setPayload(messageUtil.appendMessagePayload(node.getPayload(), Notification.getPayload()));
                mergedNotifications.put(key, node);
            }
            mergedNotifications.putIfAbsent(key, Notification);
        });
        return new ArrayList<Notifications>(mergedNotifications.values());
    }
    
    
    /*
     * 시뮬레이터 시작 
     * */
    public void simulateIncomingNotification(final long dossierId) {
        final String randomData = messageUtil.getMessagePayload(); // keep the same payload data per cluster node
        for (final String node : nodeNames) { // create a notification per cluster node
            log.info("simulateIncomingNotification: " + node);
            final Notifications notification = new Notifications();
            notification.setDossierId(dossierId);
            notification.setTimestamp(new Date());
            notification.setNodeId(node);
            notification.setPayload(randomData);
            dbService.save(notification);
        }
        dbService.flush(); // force the changes to the DB
    }
    
    /*
     * 시뮬레이터 작업 완료 후 나가는 부분 
     * */
    public void simulateOutgoingNotification(final List<Notifications> notifications) {
        for (final Notifications Notification : mergeNotificationPayloads(notifications)) {
        	log.info("simulateOutgoingNotification: " + Notification);
            getPollingQueue().stream()
                    .filter(e -> e.getDossierId() == Notification.getDossierId())
                    .forEach((final LongPollingSession lps) -> {
                        try {
                            lps.getDeferredResult().setResult(Notification.getPayload());
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    });
        }
        getPollingQueue().removeIf(e -> e.getDeferredResult().isSetOrExpired());
    }

    public BlockingQueue<LongPollingSession> getPollingQueue() {
       	for (Iterator iterator = longPollingQueue.iterator(); iterator.hasNext();) {
			LongPollingSession longPollingSession = (LongPollingSession) iterator.next();
	       	log.info("BlockingQueue: " + longPollingSession.getDossierId() + "////" );
			
		}
        return longPollingQueue;
    }
}