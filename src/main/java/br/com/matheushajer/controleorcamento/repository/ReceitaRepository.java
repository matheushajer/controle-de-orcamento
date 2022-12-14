package br.com.matheushajer.controleorcamento.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.matheushajer.controleorcamento.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	@Query(value = "select *from receita where UPPER(descricao) = UPPER(?1) and MONTH(data) = ?2 and YEAR(data) = ?3", nativeQuery = true)
	public Optional<Receita> findByDescricaoAndData(String descricao, Integer mes, Integer ano);
	
	@Query(value = "select *from receita where UPPER(descricao) = UPPER(?1)", nativeQuery = true)
	public List<Receita> findByDescricao(String descricao);
	
	public List<Receita> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select SUM(valor) FROM receita WHERE data >= ?1 AND data <= ?2", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(LocalDate dataInicial, LocalDate dataFinal);
	
}
