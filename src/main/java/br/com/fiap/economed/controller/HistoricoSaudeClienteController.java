package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoSaudeCliente.AtualizacaoHistoricoSaudeClienteDto;
import br.com.fiap.economed.service.HistoricoSaudeClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.historicoSaudeCliente.CadastroHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.DetalhesHistoricoSaudeClienteDto;
import br.com.fiap.economed.model.HistoricoSaudeCliente;
import br.com.fiap.economed.repository.HistoricoSaudeClienteRepository;

@RestController
@RequestMapping("/clientes")
public class HistoricoSaudeClienteController {

    private final HistoricoSaudeClienteService historicoSaudeClienteService;

    public HistoricoSaudeClienteController(HistoricoSaudeClienteService historicoSaudeClienteService) {
        this.historicoSaudeClienteService = historicoSaudeClienteService;
    }

    @GetMapping("/{clienteId}/historico-saude")
    public ResponseEntity<DetalhesHistoricoSaudeClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteService.buscarHistoricoSaude(clienteId);
        return ResponseEntity.ok(historicoSaude);
    }

    @PostMapping("/historico-saude")
    @Transactional
    public ResponseEntity<HistoricoSaudeCliente> cadastrar(
            @RequestBody CadastroHistoricoSaudeClienteDto historicoSaudeClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoSaude = historicoSaudeClienteService.cadastrarHistoricoSaude(historicoSaudeClienteDto);

        return ResponseEntity.created(uriBuilder.path("/clientes/{clienteId}/historico-saude")
                .buildAndExpand(historicoSaude.getClienteId()).toUri())
                .body(historicoSaude);
    }

    @PutMapping("/{clienteId}/historico-saude")
    @Transactional
    public ResponseEntity<HistoricoSaudeCliente> atualizar(@PathVariable("clienteId") Long clienteId,
                                                                         @RequestBody AtualizacaoHistoricoSaudeClienteDto historicoSaudeCliente) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteService.atualizarHistoricoSaude(clienteId, historicoSaudeCliente);
        return ResponseEntity.ok(historicoSaude);
    }

}
