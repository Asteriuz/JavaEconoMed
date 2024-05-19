package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.enderecoCliente.DetalhesEnderecoClienteDTO;
import br.com.fiap.economed.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco-cliente")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesEnderecoClienteDTO>> pesquisar(Pageable pageable) {
        var page = enderecoClienteRepository.findAll(pageable).map(DetalhesEnderecoClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEnderecoClienteDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesEnderecoClienteDTO(enderecoClienteRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }
}
