package br.com.botWarning.mappers;

import org.mapstruct.factory.Mappers;

import br.com.botWarning.domains.Aviso;
import br.com.botWarning.dtos.AvisoDTO;

public interface AvisoMapper {
	
AvisoMapper INSTANCE = Mappers.getMapper( AvisoMapper.class );	 
    
    AvisoDTO entityToDto(Aviso aviso);    
    Aviso dtoToEntity(AvisoDTO dto);

}
