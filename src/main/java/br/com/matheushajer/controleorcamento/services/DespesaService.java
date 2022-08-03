package br.com.matheushajer.controleorcamento.services;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.controleorcamento.dto.DespesaDTO;
import br.com.matheushajer.controleorcamento.entities.Despesa;
import br.com.matheushajer.controleorcamento.repositories.DespesaRepository;
import br.com.matheushajer.controleorcamento.services.execeptions.ResourceNotFoundException;
import br.com.matheushajer.controleorcamento.services.execeptions.UnicException;
import br.com.matheushajer.controleorcamento.services.execeptions.ValidationException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;

	@Transactional(readOnly = true)
	public Page<DespesaDTO> findAllPaged(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new DespesaDTO(x));
	}

	@Transactional(readOnly = true)
	public DespesaDTO findById(Long id) {
		Optional<Despesa> optional = repository.findById(id);
		return new DespesaDTO(optional.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado")));
	}

	@Transactional
	public DespesaDTO insert(DespesaDTO dto) {
		try {
			Despesa entity = new Despesa();
			copyDtoToEntity(dto, entity);
			return new DespesaDTO(repository.save(entity));
		} catch (ConstraintViolationException e) {
			throw new ValidationException("Todos os valores devem ser preenchidos");
		}catch (ConverterNotFoundException e) {
			throw new UnicException("Despesa: " +dto.getDescricao() +" já cadastradada no mês de " +dto.getData().getMonth());
		}
	}

	@Transactional
	public DespesaDTO updade(Long id, DespesaDTO dto) {
		try {
			Despesa entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new DespesaDTO(repository.save(entity));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}catch (ConverterNotFoundException e) {
			throw new UnicException("Despesa: " +dto.getDescricao() +" já cadastradada no mês de " +dto.getData().getMonth());
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}
	}

	private void copyDtoToEntity(DespesaDTO dto, Despesa entity) {
		
		Collection<DespesaDTO> lista = repository.findByDescricaoAndData(dto.getDescricao(), dto.getData().getMonthValue(), dto.getData().getYear());
		
		if (lista.isEmpty()) {
			entity.setData(dto.getData());
			entity.setDescricao(dto.getDescricao());
			entity.setValor(dto.getValor());
		}if(lista.contains(dto)) {
			throw new ValidationException("Registro duplicado");
		}
	}
}