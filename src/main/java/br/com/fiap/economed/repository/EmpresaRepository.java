package br.com.fiap.economed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    
}
