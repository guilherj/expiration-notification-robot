package br.com.expirationNotificationRobot.domains;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
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
public class Notification implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter @Setter
	@Column(name = "mensagem")
	private String message;
	
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "quando_notificar")
	private WhenNotify whenNotify;
	
	@Getter @Setter
	@Column(name = "dias_antes")
	private Integer daysBefore;	
	
	@Getter @Setter
	@Column(name = "reenviar", nullable = false)
	private Boolean resend;
	
	@Getter @Setter
	@Column(name = "quantidade_envio")
	private Integer quantityShipping;
	
	@Getter @Setter
	@Column(name = "intervalo_dias")
	private Integer intervalDays;	
	
	@Getter @Setter
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "notifications", fetch = FetchType.LAZY)
	private Set<Client> clients = new HashSet<>();
	

}
