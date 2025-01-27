package br.com.expirationNotificationRobot.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.expirationNotificationRobot.domains.NotificationHistory;

@Repository
public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {
	
	List<NotificationHistory> findByClientIdAndNotificationId (Long clientId, Long notificationId);
	List<NotificationHistory> findByDateSendBetween (LocalDate startDate, LocalDate endDate);

}
