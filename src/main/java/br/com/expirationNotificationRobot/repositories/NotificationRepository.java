package br.com.expirationNotificationRobot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	Boolean existsByMessage(String message);
	Optional<Notification> findByWhenNotify(WhenNotify whenNotify);

}
