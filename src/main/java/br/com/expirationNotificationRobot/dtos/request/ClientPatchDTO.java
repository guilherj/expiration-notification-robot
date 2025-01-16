package br.com.expirationNotificationRobot.dtos.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientPatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@NotNull(message = "O vencimento do cliente é obrigatório!")
	private LocalDateTime dueDate;
	
}
