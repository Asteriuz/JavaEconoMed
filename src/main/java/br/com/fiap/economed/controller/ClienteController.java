package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.service.ClienteService;
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

import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDTO;

@Slf4j
@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Listar clientes", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesClienteDTO>> list(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable paginacao) {
        log.info("Iniciando listagem de clientes");
        Page<DetalhesClienteDTO> paginaClientes = clienteService.listarClientes(paginacao);
        return ResponseEntity.ok(paginaClientes);
    }

    @GetMapping("/{clienteId}")
    @Operation(summary = "Buscar cliente", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesClienteDTO> search(@PathVariable Long clienteId) {
        log.info("Buscando cliente com ID: {}", clienteId);
        DetalhesClienteDTO cliente = clienteService.buscarCliente(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar cliente", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesClienteDTO> create(@RequestBody CadastroClienteDTO clienteDTO,
            UriComponentsBuilder uriBuilder) {
        log.info("Cadastrando cliente: {}", clienteDTO);
        Cliente cliente = clienteService.cadastrarCliente(clienteDTO);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));

    }

    @PutMapping("/{clienteId}")
    @Transactional
    @Operation(summary = "Atualizar cliente", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesClienteDTO> update(@PathVariable Long clienteId,
            @Valid @RequestBody AtualizacaoClienteDTO clienteDTO) {
        log.info("Atualizando cliente com ID: {}", clienteId);
        Cliente cliente = clienteService.atualizarCliente(clienteId, clienteDTO);
        return ResponseEntity.ok(new DetalhesClienteDTO(cliente));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    @Operation(summary = "Remover cliente", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
        log.info("Removendo cliente com ID: {}", clienteId);
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

}
