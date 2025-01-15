package br.com.expirationNotificationRobot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.expirationNotificationRobot.domains.enums.Status;

public class Util {
	
	public static Status validClientStatus(LocalDateTime dueDate) {		
		LocalDate now = LocalDate.now();
		
		if(dueDate.toLocalDate().isBefore(now)) {
			return Status.EXPIRADO;
			
		} else if(dueDate.toLocalDate().equals(now)) {
			return Status.VENCENDO_HOJE;
			
		} else {
			return Status.A_VENCER;
		}		
	}

}
