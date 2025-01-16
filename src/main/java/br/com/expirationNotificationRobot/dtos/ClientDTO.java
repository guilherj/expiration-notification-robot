package br.com.expirationNotificationRobot.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	
	@NotBlank(message = "O nome do cliente é obrigatório!")
	private String name;	
	
	@NotBlank(message = "O Celular do cliente é obrigatório!")
	private String cellPhone;
	
	@NotBlank(message = "O nome do país é obrigatório!")
	private String country;
	
	@NotNull(message = "O vencimento do cliente é obrigatório!")
	private LocalDateTime dueDate;	
	
	private String status;	

}
