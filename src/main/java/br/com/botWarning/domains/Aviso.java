package br.com.botWarning.domains;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.botWarning.domains.enums.QuandoAvisar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Aviso implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	@Getter @Setter
	private String mensagem;
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	private QuandoAvisar quandoAvisar;
	@Getter @Setter
	private Integer diasAntes;	
	@Getter @Setter
	@Column(nullable = false)
	private Boolean reenviar;
	@Getter @Setter
	private Integer qtdEnvio;
	@Getter @Setter
	private Integer intervaloDias;	
	
	@Getter @Setter
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "avisos", fetch = FetchType.LAZY)
	private Set<Cliente> clientes = new HashSet<>();
	

}
