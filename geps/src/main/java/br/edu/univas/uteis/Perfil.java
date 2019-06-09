package br.edu.univas.uteis;

public enum Perfil {

	ADMIN("ADMIN"),
	SUPERVISORA("SUPERVISORA"),
	PROFESSOR("PROFESSOR"),
	ESTAGIARIO("ESTAGIARIO"),
	FUNCIONARIO("FUNCIONARIO");
	
	private final String value;
	
	private Perfil(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
