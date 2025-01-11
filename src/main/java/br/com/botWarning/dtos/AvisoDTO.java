package br.com.botWarning.dtos;

import java.io.Serializable;

import br.com.botWarning.domains.enums.QuandoAvisar;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvisoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "A mensagem de aviso é obrigatória.")
	private String mensagem;
	@NotBlank(message = "O campo Quando avisar é obrigatório.")
	private QuandoAvisar quandoAvisar;	
	private Integer diasAntes;	
	private Boolean reenviar;
	private Integer qtdEnvio;
	@NotBlank(message = "O campo intervaloDias é obrigatório.")
	private Integer intervaloDias;
}
