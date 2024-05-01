package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDto;
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
    public ResponseEntity<Page<DetalhesClienteDto>> list(Pageable paginacao) {
        var paginaClientes = clienteService.listarClientes(paginacao);
        return ResponseEntity.ok(paginaClientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<DetalhesClienteDto> search(@PathVariable Long clienteId)
            throws EntityNotFoundException {
        var cliente = clienteService.buscarCliente(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> create(@RequestBody CadastroClienteDto clienteDto,
                                                   UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrarCliente(clienteDto);

        return ResponseEntity.created(uriBuilder.path("/clientes/{clienteId}").buildAndExpand(cliente.getId()).toUri())
                .body(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> update(@PathVariable Long clienteId,
                                                    @RequestBody AtualizacaoClienteDto clienteDto)
            throws EntityNotFoundException {
        var cliente = clienteService.atualizarCliente(clienteId, clienteDto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) throws EntityNotFoundException {
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

}
