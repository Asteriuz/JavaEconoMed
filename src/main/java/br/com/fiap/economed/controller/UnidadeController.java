package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.UnidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDTO;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDTO;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDTO;
import br.com.fiap.economed.model.Unidade;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesUnidadeDTO>> listar(Pageable paginacao) {
        var paginaUnidades = unidadeService.listarUnidades(paginacao);
        return ResponseEntity.ok(paginaUnidades);
    }

    @GetMapping("/{unidadeId}")
    public ResponseEntity<DetalhesUnidadeDTO> buscar(@PathVariable("unidadeId") Long unidadeId)
            throws EntityNotFoundException {
        var unidade = unidadeService.buscarUnidade(unidadeId);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUnidadeDTO> cadastrar(@RequestBody CadastroUnidadeDTO unidadeDTO,
            UriComponentsBuilder uriBuilder) {
        var unidade = unidadeService.cadastrarUnidade(unidadeDTO);

        var uri = uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUnidadeDTO(unidade));
    }

    @PutMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Unidade> atualizar(@PathVariable("unidadeId") Long unidadeId,
            @RequestBody AtualizacaoUnidadeDTO unidadeDTO) throws EntityNotFoundException {
        var unidade = unidadeService.atualizarUnidade(unidadeId, unidadeDTO);
        return ResponseEntity.ok(unidade);
    }

    @DeleteMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("unidadeId") Long unidadeId) throws EntityNotFoundException {
        unidadeService.removerUnidade(unidadeId);
        return ResponseEntity.noContent().build();
    }
}
