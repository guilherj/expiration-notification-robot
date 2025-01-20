package br.com.expirationNotificationRobot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import br.com.expirationNotificationRobot.exceptions.DataIntegratyViolationException;
import br.com.expirationNotificationRobot.exceptions.ObjectNotFoundException;
import br.com.expirationNotificationRobot.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository repository;

	public Notification findById(Long id) {		
		
		Optional<Notification> notification = repository.findById(id);
		return notification.orElseThrow(() -> new ObjectNotFoundException("Notificação com id " + id + " não encontrado."));
	}
	
	public List<Notification> findAll() {		
		return repository.findAll();
		
	}
	
	public Notification save(Notification notification) {
		
		validatingNotification(notification);		
		
		return repository.save(notification);
	}
	
	public void validatingNotification(Notification notification) {
		
		if(repository.existsByMessage(notification.getMessage())) {
			throw new DataIntegratyViolationException("Notificação já cadastrada.");
		}
	}


}
