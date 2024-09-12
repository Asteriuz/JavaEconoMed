package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoSaudeCliente.DetalhesHistoricoSaudeClienteDTO;
import br.com.fiap.economed.repository.HistoricoSaudeClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/historico-saude")
@Tag(name = "Histórico de Saúde")
public class HistoricoSaudeClienteController {

    @Autowired
    private HistoricoSaudeClienteRepository historicoSaudeClienteRepository;

    @GetMapping
    @Operation(summary = "Listar histórico de saúde", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesHistoricoSaudeClienteDTO>> pesquisar(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable pageable) {
        log.info("Listando histórico de saúde");
        var page = historicoSaudeClienteRepository.findAll(pageable).map(DetalhesHistoricoSaudeClienteDTO::new);
        log.info("Listagem de histórico de saúde finalizada com o seguinte resultado: {}", page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar histórico de saúde", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesHistoricoSaudeClienteDTO> pesquisar(@PathVariable("id") Long id) {
        log.info("Buscando histórico de saúde com ID: {}", id);
        var endereco = new DetalhesHistoricoSaudeClienteDTO(historicoSaudeClienteRepository.getReferenceById(id));
        log.info("Histórico de saúde encontrado: {}", endereco);
        return ResponseEntity.ok(endereco);
    }
}
