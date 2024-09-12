package br.com.fiap.economed.repository;

import br.com.fiap.economed.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
