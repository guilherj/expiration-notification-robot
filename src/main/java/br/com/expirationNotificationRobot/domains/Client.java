package br.com.expirationNotificationRobot.domains;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "cellPhone"})
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	
	@Getter @Setter
	@Column(name = "name")
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
	private String status;	
	
	@Getter @Setter
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "client_notification",
			joinColumns = @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "notification_id"))	
	private Set<Notification> notifications = new HashSet<>();

}
