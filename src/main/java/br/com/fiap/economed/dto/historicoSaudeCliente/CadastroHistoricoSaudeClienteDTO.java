package br.com.fiap.economed.dto.historicoSaudeCliente;

import java.time.LocalDate;

public record CadastroHistoricoSaudeClienteDTO(
                LocalDate dataRegistro,
                Boolean fuma,
                String observacoes) {
}