package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.historicoSaudeCliente.AtualizacaoHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.CadastroHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.DetalhesHistoricoSaudeClienteDto;
import br.com.fiap.economed.model.HistoricoSaudeCliente;
import br.com.fiap.economed.repository.HistoricoSaudeClienteRepository;
import br.com.fiap.economed.service.interfaces.IHistoricoSaudeClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HistoricoSaudeClienteService implements IHistoricoSaudeClienteService {

    private final HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    public HistoricoSaudeClienteService(HistoricoSaudeClienteRepository historicoSaudeClienteRepository) {
        this.historicoSaudeClienteRepository = historicoSaudeClienteRepository;
    }

    @Override
    public DetalhesHistoricoSaudeClienteDto buscarHistoricoSaude(Long clienteId) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Histórico de saúde não encontrado para o cliente com ID: " + clienteId));

        return (new DetalhesHistoricoSaudeClienteDto(historicoSaude));
    }

    @Override
    public HistoricoSaudeCliente cadastrarHistoricoSaude(CadastroHistoricoSaudeClienteDto historicoSaudeCliente) {
        var historicoSaudeCLiente = new HistoricoSaudeCliente(historicoSaudeCliente);
        return historicoSaudeClienteRepository.save(historicoSaudeCLiente);
    }

    @Override
    public HistoricoSaudeCliente atualizarHistoricoSaude(Long clienteId, AtualizacaoHistoricoSaudeClienteDto historicoSaudeCliente) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Histórico de saúde não encontrado para o cliente com ID: " + clienteId));
        historicoSaude.atualizarDados(historicoSaudeCliente);
        return historicoSaudeClienteRepository.save(historicoSaude);
    }
}
