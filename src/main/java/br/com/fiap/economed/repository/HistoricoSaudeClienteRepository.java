package br.com.fiap.economed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.HistoricoSaudeCliente;

public interface HistoricoSaudeClienteRepository extends JpaRepository<HistoricoSaudeCliente, Long> {

}
