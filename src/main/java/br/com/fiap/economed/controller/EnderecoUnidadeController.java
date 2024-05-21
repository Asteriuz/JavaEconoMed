package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.enderecoUnidade.DetalhesEnderecoUnidadeDTO;
import br.com.fiap.economed.repository.EnderecoUnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco-unidade")
public class EnderecoUnidadeController {

    @Autowired
    private EnderecoUnidadeRepository enderecoUnidadeRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesEnderecoUnidadeDTO>> pesquisar(Pageable pageable) {
        var page = enderecoUnidadeRepository.findAll(pageable).map(DetalhesEnderecoUnidadeDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesEnderecoUnidadeDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesEnderecoUnidadeDTO(enderecoUnidadeRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }
}
