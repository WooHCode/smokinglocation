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
import teamproject.smokinglocation.config.JwtProvider;
import teamproject.smokinglocation.entity.Notifications;
import teamproject.smokinglocation.repository.MemberRepository;
import teamproject.smokinglocation.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@EnableCaching
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    @Autowired
    private NotificationRepository dao;

    private Date getTimestamp() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -10);
        return cal.getTime();
    }
    public List<Notifications> getNotifications(final String nodeId) {
        return dao.getNotifications(nodeId, getTimestamp());
    }

    public List<Notifications> getAndRemoveNotifications(final String nodeId) {
    	log.info("getAndRemoveNotifications: " + nodeId);
        final List<Notifications> notifications = getNotifications(nodeId);

        // Create a copy of the list before we delete the database entities
        // DATABASE ENTITIES 삭제하기 전 notifications 복사  
        final List<Notifications> clonedNotifications = new ArrayList<Notifications>(notifications);

        // delete the database entities so we don't repeat notification sending
        // notification sending 을 반복하지 않기위해 DATABASE ENTITIES 삭제
        notifications.stream().forEach(node -> dao.delete(node));
        flush();

        // return the copied list
        return clonedNotifications;
    }

    public boolean containsNotifications(final String nodeId) {
    	log.info("containsNotifications :::: " + nodeId +"/////"+ !dao.getNotifications(nodeId, getTimestamp()).isEmpty());
        return !dao.getNotifications(nodeId, getTimestamp()).isEmpty();
    }

    public Notifications save(final Notifications node) {
        return dao.save(node);
    }

    public void flush() {
        dao.flush();
    }

}
