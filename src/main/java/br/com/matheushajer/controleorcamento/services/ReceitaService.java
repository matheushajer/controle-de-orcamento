package br.com.matheushajer.controleorcamento.services;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.entities.Receita;
import br.com.matheushajer.controleorcamento.repositories.ReceitaRepository;
import br.com.matheushajer.controleorcamento.services.execeptions.ResourceNotFoundException;
import br.com.matheushajer.controleorcamento.services.execeptions.validationException;

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

	public ReceitaDTO insert(ReceitaDTO dto) {
		try {
		Receita entity = new Receita();
		copyDtoToEntity(dto,entity);
		return new ReceitaDTO(repository.save(entity));
		}catch (ConstraintViolationException e) {
			throw new validationException("Todos os valores devem ser preenchidos");
		}
		
	}

	private void copyDtoToEntity(ReceitaDTO dto, Receita entity) {
		entity.setData(dto.getData());
		entity.setDescricao(dto.getDescricao());
		entity.setValor(dto.getValor());		
	}

	
}
