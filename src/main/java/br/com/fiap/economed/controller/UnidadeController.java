package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    // TODO: Implementar paginaçâo e ordenação, criar statusCode exceptions

    @GetMapping
    public ResponseEntity<List<DetalhesUnidadeDto>> listar() {
        var unidades = unidadeRepository.findAll().stream().map(DetalhesUnidadeDto::new).toList();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{unidadeId}")
    public ResponseEntity<DetalhesUnidadeDto> buscar(@PathVariable("unidadeId") Long unidadeId)
            throws ChangeSetPersister.NotFoundException {
        var unidade = unidadeRepository.findById(unidadeId).orElseThrow(ChangeSetPersister.NotFoundException::new);
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
            @RequestBody AtualizacaoUnidadeDto clienteDto) throws ChangeSetPersister.NotFoundException {
        var unidade = unidadeRepository.findById(unidadeId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        unidade.atualizarDados(clienteDto);
        return ResponseEntity.ok(new DetalhesUnidadeDto(unidade));
    }

    @DeleteMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("unidadeId") Long unidadeId) {
        unidadeRepository.deleteById(unidadeId);
        return ResponseEntity.noContent().build();
    }
}
