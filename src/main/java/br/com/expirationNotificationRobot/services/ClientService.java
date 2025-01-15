package br.com.expirationNotificationRobot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.dtos.ClientDTO;
import br.com.expirationNotificationRobot.exceptions.ObjectNotFoundException;
import br.com.expirationNotificationRobot.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client findById(Long id) {
		
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente com id " + id + " não encontrado." ));		
	}
	
	public List<Client> findAll() {
		return repository.findAll();		
	}
	
	public Client save(/*ClientDTO dto*/) {
		
		Client client1 = new Client(null, "Guilherme", "21993622522", "Brazil", LocalDateTime.now());
		Client client2 = new Client(null, "Lidiane", "21993622522", "Brazil", LocalDateTime.now());
		Client client3 = new Client(null, "Gabriel", "21993622522", "Brazil", LocalDateTime.now());
		
		repository.save(client1);
		repository.save(client2);
		repository.save(client3);
		
		return client1;
		
	}

}
