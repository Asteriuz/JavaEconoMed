package br.com.fiap.economed.dto.historicoSaudeCliente;

import br.com.fiap.economed.model.HistoricoSaudeCliente;

public record DetalhesHistoricoSaudeClienteDto(
        Long id,
        Long clienteId,
        String fumo,
        String doencaPrincipal,
        String historicoFamiliar,
        String alergias,
        String observacoes) {

    public DetalhesHistoricoSaudeClienteDto(HistoricoSaudeCliente historicoSaudeCliente) {
        this(
                historicoSaudeCliente.getId(),
                historicoSaudeCliente.getClienteId(),
                historicoSaudeCliente.getFumo(),
                historicoSaudeCliente.getDoencaPrincipal(),
                historicoSaudeCliente.getHistoricoFamiliar(),
                historicoSaudeCliente.getAlergias(),
                historicoSaudeCliente.getObservacoes());
    }
}
