package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDto;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.repository.EmpresaRepository;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    // TODO: Implementar paginaçâo e ordenação, criar statusCode exceptions

    @GetMapping
    public ResponseEntity<List<DetalhesEmpresaDto>> listar() {
        var empresas = empresaRepository.findAll().stream().map(DetalhesEmpresaDto::new).toList();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<DetalhesEmpresaDto> buscar(@PathVariable("empresaId") Long empresaId)
            throws ChangeSetPersister.NotFoundException {
        var empresa = empresaRepository.findById(empresaId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return ResponseEntity.ok(new DetalhesEmpresaDto(empresa));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEmpresaDto> cadastrar(@RequestBody CadastroEmpresaDto empresaDto,
            UriComponentsBuilder uriBuilder) {
        var empresa = new Empresa(empresaDto);
        empresaRepository.save(empresa);
        var uri = uriBuilder.path("/cliente/{empresaId}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEmpresaDto(empresa));
    }

    @PutMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<DetalhesEmpresaDto> atualizar(@PathVariable("empresaId") Long empresaId,
            @RequestBody AtualizacaoEmpresaDto clienteDto) throws ChangeSetPersister.NotFoundException {
        var empresa = empresaRepository.findById(empresaId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        empresa.atualizarDados(clienteDto);
        return ResponseEntity.ok(new DetalhesEmpresaDto(empresa));
    }

    @DeleteMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("empresaId") Long empresaId) {
        empresaRepository.deleteById(empresaId);
        return ResponseEntity.noContent().build();
    }
}
