package br.com.fiap.economed.repository;

import br.com.fiap.economed.model.EnderecoUnidade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoUnidadeRepository extends JpaRepository<EnderecoUnidade, Long> {
    Optional<EnderecoUnidade> findByUnidadeId(Long unidadeId);

}
