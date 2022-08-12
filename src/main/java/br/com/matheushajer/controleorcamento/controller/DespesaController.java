package br.com.matheushajer.controleorcamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.matheushajer.controleorcamento.dto.DespesaDTO;
import br.com.matheushajer.controleorcamento.entities.Despesa;
import br.com.matheushajer.controleorcamento.form.DespesaAtualizarForm;
import br.com.matheushajer.controleorcamento.form.DespesaForm;
import br.com.matheushajer.controleorcamento.repository.DespesaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaRepository repository;

	@GetMapping
	public List<DespesaDTO> findAll() {
		List<Despesa> lista = repository.findAll();
		return DespesaDTO.converter(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDTO> findById(@PathVariable Long id) {
		Optional<Despesa> despesa = repository.findById(id);
		if (despesa.isPresent()) {
			return ResponseEntity.ok(new DespesaDTO(despesa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDTO> insert(@RequestBody @Valid DespesaForm form, UriComponentsBuilder builder) {
		Despesa despesa = form.converte(repository);

		if (despesa == null) {
			return ResponseEntity.unprocessableEntity().build();
		}

		repository.save(despesa);
		URI uri = builder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDTO(despesa));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDTO> update (@PathVariable Long id, @RequestBody @Valid DespesaAtualizarForm form){
		Optional<Despesa> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			Despesa despesa = form.atualizar(id,repository);
			return ResponseEntity.ok(new DespesaDTO(despesa));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar (@PathVariable Long id){
		Optional<Despesa> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
