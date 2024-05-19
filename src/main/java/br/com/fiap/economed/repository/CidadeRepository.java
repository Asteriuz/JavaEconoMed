package br.com.fiap.economed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
