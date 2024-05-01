package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.EmpresaService;
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
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesEmpresaDto>> list(Pageable paginacao) {
        var paginaEmpresas = empresaService.listarEmpresas(paginacao);
        return ResponseEntity.ok(paginaEmpresas);
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<DetalhesEmpresaDto> find(@PathVariable Long empresaId)
            throws EntityNotFoundException {
        var empresa = empresaService.buscarEmpresa(empresaId);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Empresa> create(@RequestBody CadastroEmpresaDto empresaDto,
            UriComponentsBuilder uriBuilder) {
        var empresa = empresaService.cadastrarEmpresa(empresaDto);

        return ResponseEntity.created(uriBuilder.path("/empresas/{empresaId}").buildAndExpand(empresa.getId()).toUri())
                .body(empresa);
    }

    @PutMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Empresa> update(@PathVariable("empresaId") Long empresaId,
            @RequestBody AtualizacaoEmpresaDto empresaDto) throws EntityNotFoundException {
        var empresa = empresaService.atualizarEmpresa(empresaId, empresaDto);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{empresaId}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("empresaId") Long empresaId) throws EntityNotFoundException {
        empresaService.removerEmpresa(empresaId);
        return ResponseEntity.noContent().build();
    }
}
