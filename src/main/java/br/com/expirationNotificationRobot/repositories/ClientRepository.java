package br.com.expirationNotificationRobot.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.expirationNotificationRobot.domains.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	/**
	 * Método que verifica se existe registro com o telefone informado como parâmetro na base de dados.
	 
	 * @param cellPhone
	 * @return Boolean
	 */
	Boolean existsByCellPhone(String cellPhone);
	
	/**
	 * Busca Clientes com vencimento ANTES da data enviada pelo parâmetro.
	 * @param date
	 */
	List<Client> findByDueDateBefore(LocalDate currentDate);
	
	/**
	 * Busca Clientes com vencimento DEPOIS da data enviada pelo parâmetro.
	 * @param date
	 */
	List<Client> findByDueDateAfter(LocalDate currentDate);
	
	/**
	 * Busca clientes com vencimento na data.
	 *  
	 * @param start
	 * @param end
	 * @return
	 */
	List<Client> findByDueDate(LocalDate date);

}
