package br.com.expirationNotificationRobot.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.expirationNotificationRobot.domains.NotificationHistory;
import br.com.expirationNotificationRobot.exceptions.BusinessException;
import br.com.expirationNotificationRobot.repositories.NotificationHistoryRepository;

@Service
public class NotificationHistoryService {
	
	@Autowired
	private NotificationHistoryRepository repository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private NotificationService notificationService;
	
	public List<NotificationHistory> findByClientIdAndNotificationId (Long clientId, Long notificationId) {
		
		clientService.findById(clientId);
		notificationService.findById(notificationId);
		
		return repository.findByClientIdAndNotificationId(clientId, notificationId);
	}
	
	public void saveNotificationHistory (Long clientId, Long notificationId) {
		
		if(!validateNotificationHistory(clientId, notificationId)) {
			throw new BusinessException("Histórico já cadastrado.");					
		}
		
		repository.save(new NotificationHistory(clientId, notificationId));		
		
	}
	
	public void updateNotificationHistory (NotificationHistory notificationHistory) {
		
		notificationHistory.setDateSend(LocalDate.now());		
		repository.save(notificationHistory);		
	}
	
	public void deleteNotificationHistory (NotificationHistory notificationHistory) {				
		repository.delete(notificationHistory);		
	}	
	
	
	private Boolean validateNotificationHistory (Long clientId, Long notificationId) {
		
		List<NotificationHistory> list = findByClientIdAndNotificationId(clientId, notificationId);
		
		return list.isEmpty();
		
	}

}
