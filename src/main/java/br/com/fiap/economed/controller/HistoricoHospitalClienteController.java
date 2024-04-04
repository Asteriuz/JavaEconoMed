package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoHospitalCliente.AtualizacaoHistoricoHospitalClienteDto;
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
@RequestMapping("/cliente")
public class HistoricoHospitalClienteController {

    @Autowired
    private HistoricoHospitalClienteRepository historicoSaudeClienteRepository;


    @GetMapping("/{clienteId}/historico-hospital")
    public ResponseEntity<DetalhesHistoricoHospitalClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws EntityNotFoundException {
        var historicoHospitalar = historicoSaudeClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesHistoricoHospitalClienteDto(historicoHospitalar));
    }

    @PostMapping("/historico-hospital")
    @Transactional
    public ResponseEntity<DetalhesHistoricoHospitalClienteDto> cadastrar(
            @RequestBody CadastroHistoricoHospitalClienteDto historicoHospitalClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoHospitalCliente = new HistoricoHospitalCliente(historicoHospitalClienteDto);
        historicoSaudeClienteRepository.save(historicoHospitalCliente);
        var url = uriBuilder.path("cliente/{clienteId}/historico-hospital").buildAndExpand(historicoHospitalCliente.getId())
                .toUri();
        return ResponseEntity.created(url).body(new DetalhesHistoricoHospitalClienteDto(historicoHospitalCliente));
    }

    @PutMapping("/{clienteId}/historico-hospital")
    @Transactional
    public ResponseEntity<DetalhesHistoricoHospitalClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
                                                                @RequestBody AtualizacaoHistoricoHospitalClienteDto historicoHospitalClienteDto) throws EntityNotFoundException {
        var historicoHospitalar = historicoSaudeClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        historicoHospitalar.atualizarDados(historicoHospitalClienteDto);
        return ResponseEntity.ok(new DetalhesHistoricoHospitalClienteDto(historicoHospitalar));
    }

}
