package br.com.expirationNotificationRobot.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import br.com.expirationNotificationRobot.domains.Notification;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import br.com.expirationNotificationRobot.dtos.NotificationDTO;
import br.com.expirationNotificationRobot.util.Util;

@Mapper(componentModel = "spring", imports = {Util.class})
public interface NotificationMapper {

	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

	// Converte de Entidade para DTO
	@Mapping(target = "whenNotify", source = "whenNotify", qualifiedByName = "notifyToDescription")
	NotificationDTO entityToDto(Notification notification);

	// Converte de DTO para Entidade
	@Mapping(target = "whenNotify", expression = "java(Util.validWhenNotify(dto.getDaysBefore(), dto.getResend()))")
	Notification dtoToEntity(NotificationDTO dto);


	// Método auxiliar para retornar a descrição do enum WhenNotify
	@Named("notifyToDescription")
	default String notifyToDescription(WhenNotify notify) {
		return (notify != null) ? notify.getDescription() : null;
	}	

}
