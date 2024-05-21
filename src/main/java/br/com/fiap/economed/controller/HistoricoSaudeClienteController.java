package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoSaudeCliente.DetalhesHistoricoSaudeClienteDTO;
import br.com.fiap.economed.repository.HistoricoSaudeClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico-saude")
public class HistoricoSaudeClienteController {

    @Autowired
    private HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesHistoricoSaudeClienteDTO>> pesquisar(Pageable pageable) {
        var page = historicoSaudeClienteRepository.findAll(pageable).map(DetalhesHistoricoSaudeClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesHistoricoSaudeClienteDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesHistoricoSaudeClienteDTO(historicoSaudeClienteRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }
}
