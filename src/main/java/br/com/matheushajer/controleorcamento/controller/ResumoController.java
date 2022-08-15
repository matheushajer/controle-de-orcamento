package br.com.matheushajer.controleorcamento.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheushajer.controleorcamento.dto.ResumoDTO;
import br.com.matheushajer.controleorcamento.repository.DespesaRepository;
import br.com.matheushajer.controleorcamento.repository.ReceitaRepository;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

	@Autowired
	ReceitaRepository receitaRepo;
	
	@Autowired
	DespesaRepository despesaRepo;
	
	@GetMapping("{ano}/{mes}")
	public ResponseEntity<?> findResumo(@PathVariable Integer ano, @PathVariable Integer mes) {
		LocalDate dataInicial;

		try {
			dataInicial = LocalDate.of(ano, mes, 1);
		} catch (DateTimeException e) {
			return ResponseEntity.badRequest().build();
		}

		LocalDate dataFinal = dataInicial.with(TemporalAdjusters.lastDayOfMonth());

		ResumoDTO resumoDTO = new ResumoDTO(dataInicial, dataFinal, receitaRepo, despesaRepo);

		return ResponseEntity.ok(resumoDTO);
	}

}
