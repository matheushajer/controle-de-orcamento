package br.com.matheushajer.controleorcamento.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheushajer.controleorcamento.dto.DespesaDTO;
import br.com.matheushajer.controleorcamento.entities.Despesa;
import br.com.matheushajer.controleorcamento.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;

	@Transactional
	public List<DespesaDTO> findAll(String descricao) {
		if (descricao == null) {
			List<Despesa> lista = repository.findAll();
			return DespesaDTO.converter(lista);
		} else {
			List<Despesa> lista = repository.findByDescricao(descricao);
			return DespesaDTO.converter(lista);
		}
	}

}
