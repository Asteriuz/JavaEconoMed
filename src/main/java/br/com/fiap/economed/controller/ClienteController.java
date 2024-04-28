package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.cliente.CadastroClienteDto;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.repository.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long clienteId) throws EntityNotFoundException {
        Cliente cliente = clienteService.buscarCliente(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody CadastroClienteDto clienteDto,
                                                   UriComponentsBuilder uriBuilder) {
        Cliente cliente = clienteService.cadastrarCliente(clienteDto);
        return ResponseEntity.created(uriBuilder.path("/clientes/{clienteId}").buildAndExpand(cliente.getId()).toUri())
                .body(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteId,
                                                    @RequestBody AtualizacaoClienteDto clienteDto)
            throws EntityNotFoundException {
        Cliente cliente = clienteService.atualizarCliente(clienteId, clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId) throws EntityNotFoundException {
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

}
