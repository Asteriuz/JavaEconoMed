package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.ClienteService;
import br.com.fiap.economed.service.ConvenioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDto;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDto;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDto;
import br.com.fiap.economed.model.Convenio;
import br.com.fiap.economed.repository.ConvenioRepository;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesConvenioDto>> list(Pageable paginacao) {
        var paginaConvenios = convenioService.listarConvenio(paginacao);
        return ResponseEntity.ok(paginaConvenios);
    }

    @GetMapping("/{convenioId}")
    public ResponseEntity<DetalhesConvenioDto> search(@PathVariable Long convenioId)
            throws EntityNotFoundException {
        var convenio = convenioService.buscarConvenio(convenioId);
        return ResponseEntity.ok(convenio);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Convenio> create(@RequestBody CadastroConvenioDto convenioDto,
            UriComponentsBuilder uriBuilder) {
        var convenio = convenioService.cadastrarConvenio(convenioDto);

        return ResponseEntity.created(uriBuilder.path("/convenios/{convenioId}").buildAndExpand(convenio.getId()).toUri())
                .body(convenio);
    }

    @PutMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Convenio> update(@PathVariable("convenioId") Long convenioId,
            @RequestBody AtualizacaoConvenioDto convenioDto) throws EntityNotFoundException {
        var convenio = convenioService.atualizarConvenio(convenioId, convenioDto);
        return ResponseEntity.ok(convenio);
    }

    @DeleteMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("convenioId") Long convenioId) throws EntityNotFoundException {
        convenioService.removerConvenio(convenioId);
        return ResponseEntity.noContent().build();
    }
}