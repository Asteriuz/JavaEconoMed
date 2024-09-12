package br.com.fiap.economed.controller;

import br.com.fiap.economed.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springdoc.core.annotations.ParameterObject;
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

@Slf4j
@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    @Operation(summary = "Listar empresas", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesEmpresaDTO>> list(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable paginacao) {
        log.info("Iniciando listagem de empresas");
        Page<DetalhesEmpresaDTO> paginaEmpresas = empresaService.listarEmpresas(paginacao);
        return ResponseEntity.ok(paginaEmpresas);
    }

    @GetMapping("/{empresaId}")
    @Operation(summary = "Buscar empresa", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEmpresaDTO> find(@PathVariable Long empresaId) {
        log.info("Buscando empresa com ID: {}", empresaId);
        DetalhesEmpresaDTO empresa = empresaService.buscarEmpresa(empresaId);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar empresa", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEmpresaDTO> create(@RequestBody CadastroEmpresaDTO empresaDTO,
            UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando empresa: {}", empresaDTO);
        Empresa empresa = empresaService.cadastrarEmpresa(empresaDTO);

        var uri = uriBuilder.path("/empresas/{empresaId}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEmpresaDTO(empresa));
    }

    @PutMapping("/{empresaId}")
    @Transactional
    @Operation(summary = "Atualizar empresa", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEmpresaDTO> update(@PathVariable Long empresaId,
            @Valid @RequestBody AtualizacaoEmpresaDTO empresaDTO) {
        log.info("Atualizando empresa com ID: {}", empresaId);
        Empresa empresa = empresaService.atualizarEmpresa(empresaId, empresaDTO);
        return ResponseEntity.ok(new DetalhesEmpresaDTO(empresa));
    }

    @DeleteMapping("/{empresaId}")
    @Transactional
    @Operation(summary = "Remover empresa", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Void> delete(@PathVariable Long empresaId) {
        log.info("Removendo empresa com ID: {}", empresaId);
        empresaService.removerEmpresa(empresaId);
        return ResponseEntity.noContent().build();
    }
}
