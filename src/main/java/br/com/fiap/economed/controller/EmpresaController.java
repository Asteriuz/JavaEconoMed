package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.EmpresaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.economed.model.Empresa;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesEmpresaDTO>> list(Pageable paginacao) {
        var paginaEmpresas = empresaService.listarEmpresas(paginacao);
        return ResponseEntity.ok(paginaEmpresas);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<DetalhesEmpresaDTO> find(@PathVariable Long empresaId)
            throws EntityNotFoundException {
        var empresa = empresaService.buscarEmpresa(empresaId);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Empresa> create(@RequestBody CadastroEmpresaDTO empresaDTO,
            UriComponentsBuilder uriBuilder) {
        var empresa = empresaService.cadastrarEmpresa(empresaDTO);

        return ResponseEntity.created(uriBuilder.path("/empresas/{empresaId}").buildAndExpand(empresa.getId()).toUri())
                .body(empresa);
    }

    @PutMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Empresa> update(@PathVariable("empresaId") Long empresaId,
            @RequestBody AtualizacaoEmpresaDTO empresaDTO) throws EntityNotFoundException {
        var empresa = empresaService.atualizarEmpresa(empresaId, empresaDTO);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("empresaId") Long empresaId) throws EntityNotFoundException {
        empresaService.removerEmpresa(empresaId);
        return ResponseEntity.noContent().build();
    }
}
