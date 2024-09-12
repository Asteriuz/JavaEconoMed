package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.enderecoCliente.DetalhesEnderecoClienteDTO;
import br.com.fiap.economed.repository.EnderecoClienteRepository;
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
@RequestMapping("/endereco-cliente")
@Tag(name = "Endereço do Cliente")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @GetMapping
    @Operation(summary = "Listar endereços de clientes", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesEnderecoClienteDTO>> pesquisar(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable pageable) {
        log.info("Listando endereços de clientes");
        var page = enderecoClienteRepository.findAll(pageable).map(DetalhesEnderecoClienteDTO::new);
        log.info("Listagem de endereços de clientes finalizada com o seguinte resultado: {}", page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar endereço de cliente", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesEnderecoClienteDTO> pesquisar(@PathVariable("id") Long id) {
        log.info("Buscando endereço de cliente com ID: {}", id);
        var endereco = new DetalhesEnderecoClienteDTO(enderecoClienteRepository.getReferenceById(id));
        log.info("Endereço de cliente encontrado: {}", endereco);
        return ResponseEntity.ok(endereco);
    }
}
