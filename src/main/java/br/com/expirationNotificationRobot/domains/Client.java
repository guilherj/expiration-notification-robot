package br.com.expirationNotificationRobot.domains;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.expirationNotificationRobot.domains.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cellPhone"})
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Getter @Setter
	@Column(name = "nome")
	private String name;
	
	@Getter @Setter
	@Column(name = "celular")
	private String cellPhone;
	
	@Getter @Setter
	@Column(name = "pais")
	private String country;
	
	@Getter @Setter
	@Column(name = "data_vencimento")
	private LocalDateTime dueDate;
	
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	private Status status;
	

}
