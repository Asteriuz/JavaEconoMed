package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<DetalhesClienteDto>> listar() {
        var clientes = clienteRepository.findAll().stream().map(DetalhesClienteDto::new).toList();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<DetalhesClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws ChangeSetPersister.NotFoundException {
        var cliente = clienteRepository.findById(clienteId).
                orElseThrow(ChangeSetPersister.NotFoundException::new);
        return ResponseEntity.ok(new DetalhesClienteDto(cliente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDto> cadastrar(@RequestBody CadastroClienteDto clienteDto,
            UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(clienteDto);
        clienteRepository.save(cliente);
        var uri = uriBuilder.path("/cliente/{clienteId}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDto(cliente));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<DetalhesClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
            @RequestBody AtualizacaoClienteDto clienteDto) throws ChangeSetPersister.NotFoundException {
        var cliente = clienteRepository.findById(clienteId).
                orElseThrow(ChangeSetPersister.NotFoundException::new);
        cliente.atualizarDados(clienteDto);
        return ResponseEntity.ok(new DetalhesClienteDto(cliente));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("clienteId") Long clienteId) {
        clienteRepository.deleteById(clienteId);
        return ResponseEntity.noContent().build();
    }
}
