package br.com.expirationNotificationRobot.domains;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_historico_notificacao")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cliente_id")
	private Long clientId;
	
	@Column(name="notificacao_id")
	private Long notificationId;
	
	@Column(name="data_envio")
	private LocalDate dateSend;

	public NotificationHistory(Long clientId, Long notificationId) {
		this.clientId = clientId;
		this.notificationId = notificationId;
		this.dateSend = LocalDate.now();
	}
	
	

}
