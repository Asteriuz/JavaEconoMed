package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.CadastroHistoricoSaudeClienteDto;
import br.com.fiap.economed.dto.DetalhesHistoricoSaudeClienteDto;
import br.com.fiap.economed.model.HistoricoSaudeCliente;
import br.com.fiap.economed.repository.HistoricoSaudeClienteRepository;

@RestController
@RequestMapping("/historico-saude-cliente")
public class HistoricoSaudeClienteController {

    @Autowired
    private HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesHistoricoSaudeClienteDto> cadastrar(
            @RequestBody CadastroHistoricoSaudeClienteDto historicoSaudeClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoSaudeCliente = new HistoricoSaudeCliente(historicoSaudeClienteDto);
        historicoSaudeClienteRepository.save(historicoSaudeCliente);
        var url = uriBuilder.path("historico_saude_cliente/{codigo}").buildAndExpand(historicoSaudeCliente.getId())
                .toUri();
        return ResponseEntity.created(url).body(new DetalhesHistoricoSaudeClienteDto(historicoSaudeCliente));
    }

}
