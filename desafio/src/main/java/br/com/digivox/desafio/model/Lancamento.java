package br.com.digivox.desafio.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.digivox.desafio.util.TiposLancamento;

@Entity
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
    @DecimalMin(value = "0.01")
	private BigDecimal valor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
	private Date data;
	
	@Enumerated
    private TiposLancamento tipoLancamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TiposLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TiposLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

}
