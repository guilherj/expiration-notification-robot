package br.com.expirationNotificationRobot.dtos;

import java.io.Serializable;

import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
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
	
	@NotBlank(message = "O campo Quando avisar é obrigatório.")
	private WhenNotify whenNotify;
	
	private Integer daysBefore;	
	private Boolean resend;	
	private Integer quantityShipping;
	
	@NotBlank(message = "O campo intervaloDias é obrigatório.")
	private Integer intervalDays;
}
