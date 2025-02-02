package br.com.expirationNotificationRobot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import br.com.expirationNotificationRobot.exceptions.BusinessException;
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
	
	public Notification findByWhenNotify(WhenNotify whenNotify) {		
		
		Optional<Notification> notification = repository.findByWhenNotify(whenNotify);
		return notification.orElseThrow(() -> new ObjectNotFoundException("Notificação não encontrado."));
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
			throw new BusinessException("Mensagem de notificação já cadastrada.");
		}
	}

	public Notification update(Long notificationId, Notification requestBody) {
		Notification oldNotification = findById(notificationId);		
		Notification newNotification = new Notification();
		
		newNotification.setId(notificationId);
		newNotification.setDaysBefore(requestBody.getDaysBefore());
		newNotification.setIntervalDays(requestBody.getIntervalDays());
		newNotification.setMessage(StringUtils.hasText(requestBody.getMessage()) ? requestBody.getMessage() : oldNotification.getMessage());
		newNotification.setQuantityShipping(requestBody.getQuantityShipping());
		newNotification.setResend(requestBody.getResend());
		newNotification.setWhenNotify(requestBody.getWhenNotify());		
		
		return repository.save(newNotification);
	}

	public void delete(Long id) {
		
		Notification notification = findById(id);
		
		repository.delete(notification);
		
	}


}
