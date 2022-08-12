package br.com.matheushajer.controleorcamento.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.matheushajer.controleorcamento.entities.Categoria;
import br.com.matheushajer.controleorcamento.entities.Despesa;
import br.com.matheushajer.controleorcamento.repository.DespesaRepository;

public class DespesaForm {

	@NotNull @Length(min = 3) 
	@NotEmpty(message = "Campo Obrigatório") 
	private String descricao;
	
	@NotNull(message = "Campo Obrigatório") 
	private BigDecimal valor;
	
	@NotNull(message = "Campo Obrigatório") 
	private LocalDate data;
	
	private Categoria categoria = Categoria.OUTRAS;
	
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Despesa converte(DespesaRepository repository) {
		Optional<Despesa> despesa = repository.findByDescricaoAndData(descricao, data.getMonthValue(), data.getYear());
		
		if(despesa.isPresent()) {
			return null;
		}
		
		return new Despesa(descricao, valor, data, categoria);
	}
	
}
