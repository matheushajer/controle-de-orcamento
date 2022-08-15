package br.com.matheushajer.controleorcamento.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.matheushajer.controleorcamento.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query(value = "select *from despesa where UPPER(descricao) = UPPER(?1) and MONTH(data) = ?2 and YEAR(data) = ?3", nativeQuery = true)
	public Optional<Despesa> findByDescricaoAndData(String descricao, Integer mes, Integer ano);
	
	@Query(value = "select *from despesa where UPPER(descricao) = UPPER(?1)", nativeQuery = true)
	public List<Despesa> findByDescricao(String descricao);
	
	public List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select SUM(valor) FROM despesa WHERE data >= ?1 AND data <= ?2", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(LocalDate dataInicial, LocalDate dataFinal);

	
	@Query(value = "select categoria, SUM(valor) from despesa WHERE data >= ?1 AND data <= ?2 GROUP BY categoria", nativeQuery = true)
	public Collection<?> findTotalPorCategoria(LocalDate dataInicial, LocalDate dataFinal);
	
}
