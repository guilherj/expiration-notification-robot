package br.com.expirationNotificationRobot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.expirationNotificationRobot.domains.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Boolean existsByCellPhone(String cellPhone);

}
