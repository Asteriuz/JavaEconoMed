package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.historicoSaudeCliente.AtualizacaoHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.CadastroHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.DetalhesHistoricoSaudeClienteDto;
import br.com.fiap.economed.model.HistoricoSaudeCliente;
import jakarta.persistence.EntityNotFoundException;

public interface IHistoricoSaudeClienteService {
    DetalhesHistoricoSaudeClienteDto buscarHistoricoSaude(Long clienteId) throws EntityNotFoundException;
    HistoricoSaudeCliente cadastrarHistoricoSaude(CadastroHistoricoSaudeClienteDto historicoSaudeCliente);
    HistoricoSaudeCliente atualizarHistoricoSaude(Long clienteId, AtualizacaoHistoricoSaudeClienteDto historicoSaudeCliente) throws EntityNotFoundException;
}
