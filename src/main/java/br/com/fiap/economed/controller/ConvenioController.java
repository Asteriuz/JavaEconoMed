package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    // TODO: Implementar paginaçâo e ordenação, criar statusCode exceptions

    @GetMapping
    public ResponseEntity<List<DetalhesConvenioDto>> listar() {
        var convenios = convenioRepository.findAll().stream().map(DetalhesConvenioDto::new).toList();
        return ResponseEntity.ok(convenios);
    }

    @GetMapping("/{convenioId}")
    public ResponseEntity<DetalhesConvenioDto> buscar(@PathVariable("convenioId") Long convenioId)
            throws ChangeSetPersister.NotFoundException {
        var convenio = convenioRepository.findById(convenioId).orElseThrow(ChangeSetPersister.NotFoundException::new);
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
            @RequestBody AtualizacaoConvenioDto clienteDto) throws ChangeSetPersister.NotFoundException {
        var convenio = convenioRepository.findById(convenioId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        convenio.atualizarDados(clienteDto);
        return ResponseEntity.ok(new DetalhesConvenioDto(convenio));
    }

    @DeleteMapping("/{convenioId}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("convenioId") Long convenioId) {
        convenioRepository.deleteById(convenioId);
        return ResponseEntity.noContent().build();
    }
}