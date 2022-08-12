package br.com.matheushajer.controleorcamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.matheushajer.controleorcamento.entities.Receita;

public class ReceitaDTO {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;

	public ReceitaDTO(Receita receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
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

	public static List<ReceitaDTO> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDTO::new).collect(Collectors.toList());
	}
	
}
