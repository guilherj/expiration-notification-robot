package br.com.expirationNotificationRobot.util;

import java.time.LocalDate;
import java.util.Objects;

import br.com.expirationNotificationRobot.constants.NotificationRobotConstants;
import br.com.expirationNotificationRobot.domains.enums.Status;
import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import br.com.expirationNotificationRobot.exceptions.BusinessException;

public class Util {
	
	public static Status validClientStatus(LocalDate dueDate) {		
		LocalDate now = NotificationRobotConstants.CURRENTDATE;
		
		if(dueDate.isBefore(now)) {
			return Status.EXPIRADO;
			
		} else if(dueDate.equals(now)) {
			return Status.VENCENDO_HOJE;
			
		} else {
			return Status.A_VENCER;
		}		
	}

	public static WhenNotify validWhenNotify(Integer daysBefore, Boolean resend) {		
		WhenNotify whenNotify = null;
		
		if(daysBefore > 0 && resend.equals(Boolean.TRUE)) {
			whenNotify = WhenNotify.ANTES_DO_VENCIMENTO;
			
		} else if(daysBefore == 0 && resend.equals(Boolean.FALSE)) {
			whenNotify = WhenNotify.NO_VENCIMENTO;
			
		} else if(daysBefore < 0) {
			whenNotify = WhenNotify.DEPOIS_DO_VENCIMENTO;			
		}
		
		if(Objects.isNull(whenNotify)) {
			throw new BusinessException("Condições inválidas para criar notificação.");
		}
		
		return whenNotify;		
	}

}
