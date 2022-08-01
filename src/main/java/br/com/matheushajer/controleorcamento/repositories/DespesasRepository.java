package br.com.matheushajer.controleorcamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheushajer.controleorcamento.entities.Despesa;

@Repository
public interface DespesasRepository extends JpaRepository<Despesa, Long>{

}
