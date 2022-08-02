package br.com.matheushajer.controleorcamento.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.matheushajer.controleorcamento.entities.Receita;

public class ReceitaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;

	public ReceitaDTO() {
	}

	public ReceitaDTO(Long id, String descricao, BigDecimal valor, LocalDate data) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}
	
	public ReceitaDTO(Receita entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
		this.valor = entity.getValor();
		this.data = entity.getData();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
