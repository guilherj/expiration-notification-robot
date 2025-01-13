package br.com.expirationNotificationRobot.mappers;

import org.mapstruct.factory.Mappers;

import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.dtos.NotificationDTO;

public interface NotificationMapper {
	
NotificationMapper INSTANCE = Mappers.getMapper( NotificationMapper.class );	 
    
    NotificationDTO entityToDto(Notification notification);    
    Notification dtoToEntity(NotificationDTO dto);

}
