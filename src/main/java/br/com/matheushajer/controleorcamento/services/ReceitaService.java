package br.com.matheushajer.controleorcamento.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.entities.Receita;
import br.com.matheushajer.controleorcamento.repositories.ReceitaRepository;
import br.com.matheushajer.controleorcamento.services.execeptions.ResourceNotFoundException;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ReceitaDTO> findAllPaged(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new ReceitaDTO(x));
	}

	@Transactional(readOnly = true)
	public ReceitaDTO findById(Long id) {
		Optional<Receita> optional = repository.findById(id);
		return new ReceitaDTO(optional.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado")));
	}

}
