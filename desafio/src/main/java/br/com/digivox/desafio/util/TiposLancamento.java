package br.com.digivox.desafio.util;

public enum TiposLancamento {
	
	RECEITA("RECEITA", "R"),
	DESPESA("DESPESA", "D");
	private String nome;
	private String tipo;
	
	private TiposLancamento(String nome, String tipo){
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
