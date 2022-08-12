package br.com.matheushajer.controleorcamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.matheushajer.controleorcamento.entities.Categoria;
import br.com.matheushajer.controleorcamento.entities.Despesa;

public class DespesaDTO {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;

	public DespesaDTO(Despesa despesa) {
		this.id = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public static List<DespesaDTO> converter(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDTO::new).collect(Collectors.toList());
	}
	
}
