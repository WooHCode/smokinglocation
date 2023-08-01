package teamproject.smokinglocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import teamproject.smokinglocation.entity.Notifications;

import java.util.Date;
import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {

    @Query(value = "SELECT * FROM Notifications n WHERE n.node_id = :nodeId AND n.timestamp > :timestamp", nativeQuery = true)
	public List<Notifications> getNotifications(@Param("nodeId") final String nodeId, @Param("timestamp") final Date timestamp);
    
    
}
