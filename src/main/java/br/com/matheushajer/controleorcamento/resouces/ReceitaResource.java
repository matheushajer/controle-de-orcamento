package br.com.matheushajer.controleorcamento.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.services.ReceitaService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitaResource {

	@Autowired
	private ReceitaService service;
	
	@GetMapping
	public ResponseEntity<Page<ReceitaDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "data") String orderBy
	) {
		return ResponseEntity.ok().body(service.findAllPaged(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy)));

	}

}
