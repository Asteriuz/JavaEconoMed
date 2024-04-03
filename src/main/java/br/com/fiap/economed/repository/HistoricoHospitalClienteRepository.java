package br.com.fiap.economed.repository;

import br.com.fiap.economed.model.EnderecoCliente;
import br.com.fiap.economed.model.HistoricoSaudeCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.economed.model.HistoricoHospitalCliente;

import java.util.Optional;

public interface HistoricoHospitalClienteRepository extends JpaRepository<HistoricoHospitalCliente, Long> {
    Optional<HistoricoHospitalCliente> findByClienteId(Long clienteId);

}
