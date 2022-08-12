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

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.entities.Receita;
import br.com.matheushajer.controleorcamento.form.ReceitaAtualizarForm;
import br.com.matheushajer.controleorcamento.form.ReceitaForm;
import br.com.matheushajer.controleorcamento.repository.ReceitaRepository;
import br.com.matheushajer.controleorcamento.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaRepository repository;

	@Autowired
	private ReceitaService service;

	@GetMapping
	public List<ReceitaDTO> findAll(String descricao){ 
		return service.findAll(descricao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
		Optional<Receita> Receita = repository.findById(id);
		if (Receita.isPresent()) {
			return ResponseEntity.ok(new ReceitaDTO(Receita.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDTO> insert(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder builder) {
		Receita Receita = form.converte(repository);

		if (Receita == null) {
			return ResponseEntity.unprocessableEntity().build();
		}

		repository.save(Receita);
		URI uri = builder.path("/Receitas/{id}").buildAndExpand(Receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDTO(Receita));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDTO> update(@PathVariable Long id, @RequestBody @Valid ReceitaAtualizarForm form) {
		Optional<Receita> optional = repository.findById(id);

		if (optional.isPresent()) {
			Receita Receita = form.atualizar(id, repository);
			return ResponseEntity.ok(new ReceitaDTO(Receita));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Receita> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
