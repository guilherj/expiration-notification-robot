package br.com.expirationNotificationRobot.services;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.exceptions.ObjectNotFoundException;
import br.com.expirationNotificationRobot.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client findById(Long id) {

		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente com id " + id + " não encontrado."));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client save(Client client) throws BadRequestException {		
		validatingClient(client);		
		return repository.save(client);
	}
	
	private void validatingClient(Client cliente) throws BadRequestException {
		
		if(repository.existsByCellPhone(cliente.getCellPhone())) {
			throw new BadRequestException("Cliente já cadastrado.");
		}
		
	}

}
