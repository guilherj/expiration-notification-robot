package br.com.expirationNotificationRobot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.expirationNotificationRobot.dtos.ClientDTO;
import br.com.expirationNotificationRobot.mappers.ClientMapper;
import br.com.expirationNotificationRobot.services.ClientService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		
		return ResponseEntity.ok().body(new ClientDTO(service.findById(id)));		
	}
	
	@GetMapping()
	public ResponseEntity<List<ClientDTO>> findAll() {		
		
		return ResponseEntity.ok().body(
				service.findAll().stream().map(t -> new ClientDTO(t)).collect(Collectors.toList())
				);		
	}
	
	@PostMapping(produces = { "application/json" })
	public ResponseEntity<ClientDTO> create(/*@Valid @RequestBody ClientDTO dto*/) {
		
		return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value())).body(
				new ClientDTO(service.save(/*dto*/)));
	}
	

}
