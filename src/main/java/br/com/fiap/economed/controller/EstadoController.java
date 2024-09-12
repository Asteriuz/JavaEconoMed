package br.com.fiap.economed.controller;

import br.com.fiap.economed.model.Estado;
import br.com.fiap.economed.dto.estado.AtualizacaoEstadoDTO;
import br.com.fiap.economed.dto.estado.CadastroEstadoDTO;
import br.com.fiap.economed.dto.estado.DetalhesEstadoDTO;
import br.com.fiap.economed.repository.EstadoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/estados")
@Tag(name = "Estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar estado", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEstadoDTO> cadastrar(@RequestBody CadastroEstadoDTO dto,
            UriComponentsBuilder builder) {
        log.info("Cadastrando estado: {}", dto);
        var estado = new Estado(dto);
        estado = estadoRepository.save(estado);
        var uri = builder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
        log.info("Estado cadastrado com sucesso: {}", estado);
        return ResponseEntity.created(uri).body(new DetalhesEstadoDTO(estado));
    }

    @GetMapping
    @Operation(summary = "Listar estados", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesEstadoDTO>> pesquisar(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable pageable) {
        log.info("Listando estados");
        var page = estadoRepository.findAll(pageable).map(DetalhesEstadoDTO::new);
        log.info("Listagem de estados finalizada com o seguinte resultado: {}", page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEstadoDTO> pesquisar(@PathVariable("id") Long id) {
        log.info("Buscando estado com ID: {}", id);
        var estado = new DetalhesEstadoDTO(estadoRepository.getReferenceById(id));
        log.info("Estado encontrado: {}", estado);
        return ResponseEntity.ok(estado);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar estado", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEstadoDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody AtualizacaoEstadoDTO dto) {
        log.info("Atualizando estado com ID: {}", id);
        var estado = estadoRepository.getReferenceById(id);
        estado.atualizar(dto);
        log.info("Estado atualizado com sucesso: {}", estado);
        return ResponseEntity.ok(new DetalhesEstadoDTO(estado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Remover estado", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        log.info("Removendo estado com ID: {}", id);
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado não encontrado com ID: " + id));
        estadoRepository.delete(estado);
        log.info("Estado removido com sucesso: {}", estado);
        return ResponseEntity.noContent().build();
    }

}
