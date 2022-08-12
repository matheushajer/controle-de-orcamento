package br.com.matheushajer.controleorcamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.matheushajer.controleorcamento.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query(value = "select *from despesa where UPPER(descricao) = UPPER(?1) and MONTH(data) = ?2 and YEAR(data) = ?3", nativeQuery = true)
	public Optional<Despesa> findByDescricaoAndData(String descricao, Integer mes, Integer ano);
}
