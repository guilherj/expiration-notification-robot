package br.com.botWarning.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.botWarning.domains.Cliente;
import br.com.botWarning.exceptions.ObjectNotFoundException;
import br.com.botWarning.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Long id) {
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente com id " + id + " não encontrado." ));		
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();		
	}
	
	public void save(Cliente cliente) {
		
	}

}
