package teamproject.smokinglocation.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.service.NotificationService;

import java.util.Date;


/**
 * Long Polling 테스트용 
 * 오우석 
 * 23.07.29
 * 고객센터 service로 옮겨져야함....
 */
@Component
@Slf4j
public class LongPollingEventSimulator {
    @Autowired
    private NotificationService dbService;
    
    @Value("${cluster.nodenames}")
    private String[] nodeNames;
    
    /*
     * 시뮬레이터 시작 
     * 향후 고객센터 Service 쪽으로 옮겨져야함
     * */
    public void simulateIncomingNotification(final long userId) {
    	log.info("LongPollingEventSimulator - simulateIncomingNotification START ");
        for (final String node : nodeNames) { 
        	// 클러스터 노드별로 Notification 생성하는 포문인데 정확한 의미 모르겠음. 서버 이중화 일때 사용한건지 ?
        	// 추후 확인 필요 .... 
            final Notifications notification = new Notifications();
            notification.setUserId(userId);
            notification.setTimestamp(new Date());
            notification.setNodeId(node);
            notification.setPayload(" Answer For ::: " +  userId);
            dbService.save(notification);
        }
        dbService.flush(); // force the changes to the DB
    	log.info("LongPollingEventSimulator - simulateIncomingNotification END ");
    }
   
}