package br.com.expirationNotificationRobot.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.dtos.ClientDTO;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );	 
    
    ClientDTO entityToDto(Client client);    
    Client dtoToEntity(ClientDTO dto);

}
