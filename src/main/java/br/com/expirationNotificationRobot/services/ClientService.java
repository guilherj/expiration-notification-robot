package br.com.expirationNotificationRobot.services;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.expirationNotificationRobot.constants.NotificationRobotConstants;
import br.com.expirationNotificationRobot.domains.Client;
import br.com.expirationNotificationRobot.exceptions.BusinessException;
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
		
		// Validando se já existe cliente salvo com os novos dados informados na requisição.
		validatingClient(client);		
		return repository.save(client);
	}
	
	public Client update(Long clientId, Client requestBody) throws BadRequestException {
		
		// Validando se existe um cliente com o id informado.
		Client oldClient = findById(clientId);
		
		// Validando se foi enviado telefone na requisição, em caso de simples renovação o telefone não é enviado.
		if(StringUtils.hasText(requestBody.getCellPhone())) {
			
			// Se for alterado o telefone do cliente, valida se este telefone já não está salvo por outro cliente
			if(!requestBody.getCellPhone().equals(oldClient.getCellPhone())) {
				validatingClient(requestBody);			
			}
			
		}		
		
		oldClient.setName(StringUtils.hasText(requestBody.getName()) ? requestBody.getName() : oldClient.getName());
		oldClient.setCellPhone(StringUtils.hasText(requestBody.getCellPhone()) ? requestBody.getCellPhone() : oldClient.getCellPhone());
		oldClient.setCountry(StringUtils.hasText(requestBody.getCountry()) ? requestBody.getCountry() : oldClient.getCountry());		
		oldClient.setDueDate(requestBody.getDueDate());
		oldClient.setStatus(requestBody.getStatus());
		
		// Validado tudo, salva a alteração na base de dados e retorna o cliente com suas alterações.
		return repository.save(oldClient);
	}
	
	public void delete(Long id) {
		Client client = findById(id);
		
		repository.delete(client);		
	}
	
	/**
	 * Busca na base de dados Clientes com vencimento ANTES da data atual.
	 * @param date
	 */
	public List<Client> findByDueDateBefore() {		
		return repository.findByDueDateBefore(NotificationRobotConstants.CURRENTDATE);
	}
	
	/**
	 * Busca na base de dados Clientes com vencimento DEPOIS da data atual.
	 * @param date
	 */
	public List<Client> findByDueDateAfter() {
		return repository.findByDueDateAfter(NotificationRobotConstants.CURRENTDATE);
	}
	
	/**
	 * Busca na base de dados clientes com vencimento NA DATA ATUAL.
	 *  
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Client> findByDueDateBetween() {		
		return repository.findByDueDate(NotificationRobotConstants.CURRENTDATE);
	}
	
	
	private void validatingClient(Client cliente) throws BadRequestException {
		
		if(repository.existsByCellPhone(cliente.getCellPhone())) {
			throw new BusinessException("Cliente já cadastrado.");
		}
		
	}



}
