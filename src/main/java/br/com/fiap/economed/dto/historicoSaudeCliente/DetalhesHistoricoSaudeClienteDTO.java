package br.com.fiap.economed.dto.historicoSaudeCliente;

import br.com.fiap.economed.model.HistoricoSaudeCliente;

public record DetalhesHistoricoSaudeClienteDTO(
        Long id,
        Boolean fuma,
        String observacoes) {

    public DetalhesHistoricoSaudeClienteDTO(HistoricoSaudeCliente historicoSaudeCliente) {
        this(
                historicoSaudeCliente.getId(),
                historicoSaudeCliente.getFuma(),
                historicoSaudeCliente.getObservacoes());
    }
}
