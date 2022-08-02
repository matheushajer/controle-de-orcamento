package br.com.matheushajer.controleorcamento.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.matheushajer.controleorcamento.dto.DespesaDTO;
import br.com.matheushajer.controleorcamento.entities.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{
	
	@Query(value = "select distinct from tb_despesa where descricao = ?1 and (MONTH(data)) = ?2", nativeQuery = true)
	public Collection<DespesaDTO> findByDescricaoAndData(String descricao, Integer mes);
}
