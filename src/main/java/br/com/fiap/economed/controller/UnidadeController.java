package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.UnidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesUnidadeDto>> listar(Pageable paginacao) {
        var paginaUnidades = unidadeService.listarUnidades(paginacao);
        return ResponseEntity.ok(paginaUnidades);
    }

    @GetMapping("/{unidadeId}")
    public ResponseEntity<DetalhesUnidadeDto> buscar(@PathVariable("unidadeId") Long unidadeId)
            throws EntityNotFoundException {
        var unidade = unidadeService.buscarUnidade(unidadeId);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Unidade> cadastrar(@RequestBody CadastroUnidadeDto unidadeDto,
            UriComponentsBuilder uriBuilder) {
        var unidade = unidadeService.cadastrarUnidade(unidadeDto);
        return ResponseEntity.created(uriBuilder.path("/unidades/{unidadeId}").buildAndExpand(unidade.getId()).toUri())
                .body(unidade);
    }

    @PutMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Unidade> atualizar(@PathVariable("unidadeId") Long unidadeId,
            @RequestBody AtualizacaoUnidadeDto unidadeDto) throws EntityNotFoundException {
        var unidade = unidadeService.atualizarUnidade(unidadeId, unidadeDto);
        return ResponseEntity.ok(unidade);
    }

    @DeleteMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("unidadeId") Long unidadeId) throws EntityNotFoundException {
        unidadeService.removerUnidade(unidadeId);
        return ResponseEntity.noContent().build();
    }
}
