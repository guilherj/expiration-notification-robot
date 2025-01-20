package br.com.expirationNotificationRobot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.expirationNotificationRobot.dtos.NotificationDTO;
import br.com.expirationNotificationRobot.mappers.NotificationMapper;
import br.com.expirationNotificationRobot.services.NotificationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
	
	@Autowired
	private NotificationService service;
	
	@Autowired
	private NotificationMapper mapper;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationDTO> findById(@PathVariable Long id) {				
		return ResponseEntity.ok().body(mapper.entityToDto(service.findById(id)));		
	}
	
	@GetMapping()
	public ResponseEntity<List<NotificationDTO>> findAll() {	
		return ResponseEntity.ok().body(
				service.findAll().stream().map(t -> mapper.entityToDto(t)).collect(Collectors.toList()));		
	}
	
	@PostMapping(produces = { "application/json" })
	public ResponseEntity<NotificationDTO> create(@Valid @RequestBody NotificationDTO requestBody) throws BadRequestException {		

		return ResponseEntity.status(HttpStatus.CREATED.value())
				.body(mapper.entityToDto(service.save(mapper.dtoToEntity(requestBody))));		
		
	}
	
//	@PutMapping(value = "/{clientId}")
//	public ResponseEntity<NotificationDTO> update(@PathVariable Long clientId, @Valid @RequestBody NotificationDTO requestBody) throws BadRequestException {				
//		return ResponseEntity.ok().body(mapper.entityToDto(service.update(clientId, mapper.dtoToEntity(requestBody))));		
//	}
//	
//	
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {	
//		service.delete(id);		
//		return ResponseEntity.noContent().build();		
//	}

}
