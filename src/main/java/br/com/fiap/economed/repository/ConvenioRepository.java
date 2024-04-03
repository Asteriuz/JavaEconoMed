package br.com.fiap.economed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.Convenio;


public interface ConvenioRepository extends JpaRepository<Convenio, Long>{
    
}
