package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoHospitalCliente.AtualizacaoHistoricoHospitalClienteDto;
import br.com.fiap.economed.service.HistoricoHospitalClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDto;
import br.com.fiap.economed.model.HistoricoHospitalCliente;
import br.com.fiap.economed.repository.HistoricoHospitalClienteRepository;

@RestController
@RequestMapping("/clientes")
public class HistoricoHospitalClienteController {

    private final HistoricoHospitalClienteService historicoSaudeClienteService;

    public HistoricoHospitalClienteController(HistoricoHospitalClienteService historicoSaudeClienteService) {
        this.historicoSaudeClienteService = historicoSaudeClienteService;
    }

    @GetMapping("/{clienteId}/historico-hospital")
    public ResponseEntity<DetalhesHistoricoHospitalClienteDto> search(@PathVariable Long clienteId)
            throws EntityNotFoundException {
        var historicoHospitalar = historicoSaudeClienteService.buscarHistoricoHospital(clienteId);
        return ResponseEntity.ok(historicoHospitalar);
    }

    @PostMapping("/historico-hospital")
    @Transactional
    public ResponseEntity<HistoricoHospitalCliente> cadastrar(
            @RequestBody CadastroHistoricoHospitalClienteDto historicoHospitalClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoHospitalCliente = historicoSaudeClienteService.cadastrarHistoricoHospital(historicoHospitalClienteDto);

        return ResponseEntity.created(uriBuilder.path("/clientes/{clienteId}/historico-hospital")
                .buildAndExpand(historicoHospitalCliente.getClienteId()).toUri())
                .body(historicoHospitalCliente);
    }

    @PutMapping("/{clienteId}/historico-hospital")
    @Transactional
    public ResponseEntity<HistoricoHospitalCliente> atualizar(@PathVariable("clienteId") Long clienteId,
                                                                @RequestBody AtualizacaoHistoricoHospitalClienteDto historicoHospitalClienteDto) throws EntityNotFoundException {
        var historicoHospitalCliente = historicoSaudeClienteService.atualizarHistoricoHospital(clienteId, historicoHospitalClienteDto);
        return ResponseEntity.ok(historicoHospitalCliente);
    }

}
