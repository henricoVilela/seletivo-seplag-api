package br.gov.br.seplag_api.domain.service;

public enum TipoConversao {
	SIMPLES, COMPLETA ;
	
	public boolean isCompleta() {
		return this.equals(COMPLETA);
	}
}

