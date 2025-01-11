package br.com.botWarning.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	
	A_VENCER(0, "À VENCER"), 
	VENCENDO_HOJE(1, "VENCENDO HOJE"), 
	EXPIRADO(2, "EXPIRADO");

	private Integer cod;
	private String descricao;

	
	public static Status toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod()) ) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido!" + cod);
	}

}
