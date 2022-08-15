package br.com.matheushajer.controleorcamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import br.com.matheushajer.controleorcamento.repository.DespesaRepository;
import br.com.matheushajer.controleorcamento.repository.ReceitaRepository;

public class ResumoDTO {

	private BigDecimal totalReceita;
	private BigDecimal totalDespesa;
	private BigDecimal saldo;

	private Collection<?> totalPorCategoria;

	public ResumoDTO(LocalDate dataInicial, LocalDate dataFinal, ReceitaRepository receitaRepo,
			DespesaRepository despesaRepo) {

		this.totalReceita = receitaRepo.sumBetweenData(dataInicial, dataFinal).orElse(BigDecimal.ZERO);
		this.totalDespesa = despesaRepo.sumBetweenData(dataInicial, dataFinal).orElse(BigDecimal.ZERO);

		this.saldo = this.totalReceita.subtract(this.totalDespesa);

		this.totalPorCategoria = despesaRepo.findTotalPorCategoria(dataInicial, dataFinal);
	}

	public BigDecimal getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(BigDecimal totalReceita) {
		this.totalReceita = totalReceita;
	}

	public BigDecimal getTotalDespesa() {
		return totalDespesa;
	}

	public void setTotalDespesa(BigDecimal totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Collection<?> getTotalPorCategoria() {
		return totalPorCategoria;
	}

	public void setTotalPorCategoria(Collection<?> totalPorCategoria) {
		this.totalPorCategoria = totalPorCategoria;
	}

}
