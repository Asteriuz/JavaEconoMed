package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.ConvenioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDTO;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDTO;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDTO;
import br.com.fiap.economed.model.Convenio;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesConvenioDTO>> list(Pageable paginacao) {
        var paginaConvenios = convenioService.listarConvenio(paginacao);
        return ResponseEntity.ok(paginaConvenios);
    }

    @GetMapping("/{convenioId}")
    public ResponseEntity<DetalhesConvenioDTO> search(@PathVariable Long convenioId)
            throws EntityNotFoundException {
        var convenio = convenioService.buscarConvenio(convenioId);
        return ResponseEntity.ok(convenio);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesConvenioDTO> create(@RequestBody CadastroConvenioDTO convenioDTO,
            UriComponentsBuilder uriBuilder) {
        var convenio = convenioService.cadastrarConvenio(convenioDTO);

        var uri = uriBuilder.path("/convenios/{id}").buildAndExpand(convenio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesConvenioDTO(convenio));
    }

    @PutMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Convenio> update(@PathVariable("convenioId") Long convenioId,
            @RequestBody AtualizacaoConvenioDTO convenioDTO) throws EntityNotFoundException {
        var convenio = convenioService.atualizarConvenio(convenioId, convenioDTO);
        return ResponseEntity.ok(convenio);
    }

    @DeleteMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("convenioId") Long convenioId) throws EntityNotFoundException {
        convenioService.removerConvenio(convenioId);
        return ResponseEntity.noContent().build();
    }
}