package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.economed.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesClienteDTO>> list(Pageable paginacao) {
        var paginaClientes = clienteService.listarClientes(paginacao);
        return ResponseEntity.ok(paginaClientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<DetalhesClienteDTO> search(@PathVariable Long clienteId)
            throws EntityNotFoundException {
        var cliente = clienteService.buscarCliente(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> create(@RequestBody CadastroClienteDTO clienteDTO,
            UriComponentsBuilder uriBuilder) {

        var cliente = clienteService.cadastrarCliente(clienteDTO);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));

    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<DetalhesClienteDTO> update(@PathVariable Long clienteId,
            @RequestBody AtualizacaoClienteDTO clienteDTO)
            throws EntityNotFoundException {
        var cliente = clienteService.atualizarCliente(clienteId, clienteDTO);
        return ResponseEntity.ok(new DetalhesClienteDTO(cliente));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) throws EntityNotFoundException {
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

}
