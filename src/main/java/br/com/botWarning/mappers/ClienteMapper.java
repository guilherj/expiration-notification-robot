package br.com.botWarning.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.botWarning.domains.Cliente;
import br.com.botWarning.dtos.ClienteDTO;

@Mapper
public interface ClienteMapper {
	
	ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );	 
    
    ClienteDTO entityToDto(Cliente cliente);    
    Cliente dtoToEntity(ClienteDTO dto);

}
