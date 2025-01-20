package br.com.expirationNotificationRobot.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "A mensagem de aviso é obrigatória.")
	private String message;	
	private String whenNotify;	
	private Integer daysBefore;	
	private Boolean resend;	
	private Integer quantityShipping;
	
	//@NotEmpty(message = "O campo intervalo de dias é obrigatório.")
	private Integer intervalDays;
}
