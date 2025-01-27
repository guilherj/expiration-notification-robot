package br.com.expirationNotificationRobot.domains;

import java.io.Serializable;

import br.com.expirationNotificationRobot.domains.enums.WhenNotify;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_notificacao")
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
