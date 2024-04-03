package br.com.fiap.economed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.Unidade;


public interface UnidadeRepository extends JpaRepository<Unidade, Long>{
    
}
