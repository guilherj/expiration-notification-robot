package br.com.botWarning.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.botWarning.domains.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	
	@NotBlank(message = "O nome do cliente é obrigatório!")
	private String nome;	
	
	@NotBlank(message = "O Celular do cliente é obrigatório!")
	private String celular;	
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@NotBlank(message = "O vencimento do cliente é obrigatório!")
	private LocalDateTime vencimento;	
	
	private Status status;

	public ClienteDTO(String nome, String celular, LocalDateTime vencimento) {		
		this.nome = nome;
		this.celular = celular;
		this.vencimento = vencimento;
		this.status = validandoStatus(vencimento);		
	}

	private Status validandoStatus(LocalDateTime vencimento2) {		
		LocalDateTime dataAtual = LocalDateTime.now();
		
		if(vencimento.isBefore(dataAtual)) {
			return Status.EXPIRADO;
			
		} else if(vencimento.toLocalDate().equals(dataAtual.toLocalDate())) {
			return Status.VENCENDO_HOJE;
			
		} else {
			return Status.A_VENCER;
		}		
	}
	
	

}
