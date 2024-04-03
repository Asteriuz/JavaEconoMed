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

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDto;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDto;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDto;
import br.com.fiap.economed.model.Convenio;
import br.com.fiap.economed.repository.ConvenioRepository;

import java.util.List;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioRepository convenioRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesConvenioDto>> listar(Pageable paginacao) {
        var PaginaConvenios = convenioRepository.findAll(paginacao).map(DetalhesConvenioDto::new);
        return ResponseEntity.ok(PaginaConvenios);
    }

    @GetMapping("/{convenioId}")
    public ResponseEntity<DetalhesConvenioDto> buscar(@PathVariable("convenioId") Long convenioId)
            throws EntityNotFoundException {
        var convenio = convenioRepository.findById(convenioId).
                orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesConvenioDto(convenio));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesConvenioDto> cadastrar(@RequestBody CadastroConvenioDto convenioDto,
            UriComponentsBuilder uriBuilder) {
        var convenio = new Convenio(convenioDto);
        convenioRepository.save(convenio);
        var uri = uriBuilder.path("/cliente/{convenioId}").buildAndExpand(convenio.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesConvenioDto(convenio));
    }

    @PutMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<DetalhesConvenioDto> atualizar(@PathVariable("convenioId") Long convenioId,
            @RequestBody AtualizacaoConvenioDto convenioDto) throws EntityNotFoundException {
        var convenio = convenioRepository.findById(convenioId).
                orElseThrow(EntityNotFoundException::new);
        convenio.atualizarDados(convenioDto);
        return ResponseEntity.ok(new DetalhesConvenioDto(convenio));
    }

    @DeleteMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("convenioId") Long convenioId) throws EntityNotFoundException {
        var convenio = convenioRepository.findById(convenioId).
                orElseThrow(EntityNotFoundException::new);
        convenioRepository.delete(convenio);
        return ResponseEntity.noContent().build();
    }
}