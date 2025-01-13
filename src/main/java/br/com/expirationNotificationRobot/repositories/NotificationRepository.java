package br.com.expirationNotificationRobot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.expirationNotificationRobot.domains.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
