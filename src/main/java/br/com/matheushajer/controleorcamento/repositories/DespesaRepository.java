package br.com.matheushajer.controleorcamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheushajer.controleorcamento.entities.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
