package br.com.matheushajer.controleorcamento.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.matheushajer.controleorcamento.dto.ReceitaDTO;
import br.com.matheushajer.controleorcamento.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	@Query(value = "select distinct from tb_receita where descricao = ?1 and MONTH(data) = ?2 and YEAR(data) = ?3", nativeQuery = true)
	public Collection<ReceitaDTO> findByDescricaoAndData(String descricao, Integer mes, Integer ano);
}