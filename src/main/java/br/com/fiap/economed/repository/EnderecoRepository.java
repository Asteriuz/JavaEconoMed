package br.com.fiap.economed.repository;

import br.com.fiap.economed.model.EnderecoCliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoCliente, Long> {
}
