package br.com.expirationNotificationRobot.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WhenNotify {
	
	ANTES_DO_VENCIMENTO(0, "ANTES DO VENCIMENTO"), 
	NO_VENCIMENTO(1, "NO VENCIMENTO"), 
	DEPOIS_DO_VENCIMENTO(2, "DEPOIS DO VENCIMENTO");

	private Integer cod;
	private String description;

	
	public static WhenNotify toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(WhenNotify x : WhenNotify.values()) {
			if(cod.equals(x.getCod()) ) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Propriedade inválida!" + cod);
	}

}
