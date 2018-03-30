package br.com.digivox.desafio.util;

import java.math.BigDecimal;

public class GraficoDTO {
	
	private String key;
	private BigDecimal value;
	
	public GraficoDTO(String key, BigDecimal value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
