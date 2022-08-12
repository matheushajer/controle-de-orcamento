package br.com.matheushajer.controleorcamento.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.matheushajer.controleorcamento.entities.Receita;
import br.com.matheushajer.controleorcamento.repository.ReceitaRepository;

public class ReceitaAtualizarForm {

	@NotNull @Length(min = 3) 
	@NotEmpty(message = "Campo Obrigatório") 
	private String descricao;
	
	@NotNull(message = "Campo Obrigatório") 
	private BigDecimal valor;
	
	@NotNull(message = "Campo Obrigatório") 
	private LocalDate data;
	
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
	
	public Receita atualizar(Long id, ReceitaRepository repository) {
		Receita Receita = repository.getReferenceById(id);
		Receita.setDescricao(this.descricao);
		Receita.setValor(this.valor);
		Receita.setData(this.data);
		
		return Receita;
	}
	
}
