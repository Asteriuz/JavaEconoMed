package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoHospitalCliente.AtualizacaoHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoSaudeCliente.AtualizacaoHistoricoSaudeClienteDto;
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
@RequestMapping("/cliente")
public class HistoricoSaudeClienteController {

    @Autowired
    private HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    @GetMapping("/{clienteId}/historico-saude")
    public ResponseEntity<DetalhesHistoricoSaudeClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesHistoricoSaudeClienteDto(historicoSaude));
    }

    @PostMapping("/historico-saude")
    @Transactional
    public ResponseEntity<DetalhesHistoricoSaudeClienteDto> cadastrar(
            @RequestBody CadastroHistoricoSaudeClienteDto historicoSaudeClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoSaudeCliente = new HistoricoSaudeCliente(historicoSaudeClienteDto);
        historicoSaudeClienteRepository.save(historicoSaudeCliente);
        var url = uriBuilder.path("cliente/{clienteId}/historico-saude").buildAndExpand(historicoSaudeCliente.getId())
                .toUri();
        return ResponseEntity.created(url).body(new DetalhesHistoricoSaudeClienteDto(historicoSaudeCliente));
    }

    @PutMapping("/{clienteId}/historico-saude")
    @Transactional
    public ResponseEntity<DetalhesHistoricoSaudeClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
                                                                         @RequestBody AtualizacaoHistoricoSaudeClienteDto historicoSaudeCliente) throws EntityNotFoundException {
        var historicoSaude = historicoSaudeClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        historicoSaude.atualizarDados(historicoSaudeCliente);
        return ResponseEntity.ok(new DetalhesHistoricoSaudeClienteDto(historicoSaude));
    }

}
