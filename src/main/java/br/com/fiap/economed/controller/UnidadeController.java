package br.com.fiap.economed.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDto;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDto;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDto;
import br.com.fiap.economed.model.Unidade;
import br.com.fiap.economed.repository.UnidadeRepository;

import java.util.List;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesUnidadeDto>> listar(Pageable paginacao) {
        var PaginaUnidades = unidadeRepository.findAll(paginacao).map(DetalhesUnidadeDto::new);
        return ResponseEntity.ok(PaginaUnidades);
    }

    @GetMapping("/{unidadeId}")
    public ResponseEntity<DetalhesUnidadeDto> buscar(@PathVariable("unidadeId") Long unidadeId)
            throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesUnidadeDto(unidade));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUnidadeDto> cadastrar(@RequestBody CadastroUnidadeDto unidadeDto,
            UriComponentsBuilder uriBuilder) {
        var unidade = new Unidade(unidadeDto);
        unidadeRepository.save(unidade);
        var uri = uriBuilder.path("/cliente/{unidadeId}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUnidadeDto(unidade));
    }

    @PutMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<DetalhesUnidadeDto> atualizar(@PathVariable("unidadeId") Long unidadeId,
            @RequestBody AtualizacaoUnidadeDto unidadeDto) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(EntityNotFoundException::new);
        unidade.atualizarDados(unidadeDto);
        return ResponseEntity.ok(new DetalhesUnidadeDto(unidade));
    }

    @DeleteMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("unidadeId") Long unidadeId) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(EntityNotFoundException::new);
        unidadeRepository.delete(unidade);
        return ResponseEntity.noContent().build();
    }
}
