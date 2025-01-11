package br.com.botWarning.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuandoAvisar {
	
	ANTES_DO_VENCIMENTO(0, "ANTES DO VENCIMENTO"), 
	NO_VENCIMENTO(1, "NO VENCIMENTO"), 
	DEPOIS_DO_VENCIMENTO(2, "DEPOIS DO VENCIMENTO");

	private Integer cod;
	private String descricao;

	
	public static QuandoAvisar toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(QuandoAvisar x : QuandoAvisar.values()) {
			if(cod.equals(x.getCod()) ) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Propriedade inválida!" + cod);
	}

}
