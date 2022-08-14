package br.com.matheushajer.controleorcamento.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.entities.Receita;
import br.com.matheushajer.controleorcamento.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;

	@Transactional
	public List<ReceitaDTO> findAll(String descricao) {
		if (descricao == null) {
			List<Receita> lista = repository.findAll();
			return ReceitaDTO.converter(lista);
		} else {
			List<Receita> lista = repository.findByDescricao(descricao);
			return ReceitaDTO.converter(lista);
		}
	}
	
	@Transactional
	public List<Receita> findByData(Integer ano, Integer mes) {

		LocalDate dataInicial = LocalDate.of(ano, mes, 1);
		LocalDate dataFinal = dataInicial.with(TemporalAdjusters.lastDayOfMonth());

		List<Receita> receitas = repository.findByDataBetween(dataInicial, dataFinal);
		return receitas;
	}

}
