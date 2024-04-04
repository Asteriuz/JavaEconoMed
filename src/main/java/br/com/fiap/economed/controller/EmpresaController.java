package br.com.fiap.economed.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDto;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesEmpresaDto>> listar(Pageable paginacao) {
        var PaginaEmpresas = empresaRepository.findAll(paginacao).map(DetalhesEmpresaDto::new);
        return ResponseEntity.ok(PaginaEmpresas);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<DetalhesEmpresaDto> buscar(@PathVariable("empresaId") Long empresaId)
            throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId).
                orElseThrow(EntityNotFoundException::new);
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
            @RequestBody AtualizacaoEmpresaDto empresaDto) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId).
                orElseThrow(EntityNotFoundException::new);
        empresa.atualizarDados(empresaDto);
        return ResponseEntity.ok(new DetalhesEmpresaDto(empresa));
    }

    @DeleteMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("empresaId") Long empresaId) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId).
                orElseThrow(EntityNotFoundException::new);
        empresaRepository.delete(empresa);
        return ResponseEntity.noContent().build();
    }
}
