package br.com.expirationNotificationRobot.mappers;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.domains.enums.Status;
import br.com.expirationNotificationRobot.dtos.ClientDTO;
import br.com.expirationNotificationRobot.util.Util;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );	 
    
	// Converte de Entidade para DTO
	@Mapping(target = "status", source = "status", qualifiedByName = "statusToDescription")
    ClientDTO entityToDto(Client client);
    
    // Converte de DTO para Entidade
	@Mapping(target = "status", source = "dueDate", qualifiedByName = "dueDateToStatus")
    Client dtoToEntity(ClientDTO dto);
    
    // Método auxiliar para retornar a descrição do enum Status
    @Named("statusToDescription")
    default String statusToDescription(Status status) {
        return (status != null) ? status.getDescription() : null;
    }

    // Método auxiliar para definir o status do cliente
    @Named("dueDateToStatus")
    default Status dueDateToStatus(LocalDateTime dueDate) {
        return Util.validClientStatus(dueDate);
    }

}
