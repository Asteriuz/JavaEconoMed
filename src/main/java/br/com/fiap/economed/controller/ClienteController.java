package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
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

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesClienteDto>> listar(Pageable paginacao) {
        var paginaClientes = clienteRepository.findAll(paginacao).map(DetalhesClienteDto::new);
        return ResponseEntity.ok(paginaClientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<DetalhesClienteDto> buscar(@PathVariable("clienteId") Long clienteId)
            throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId).
                orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesClienteDto(cliente));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDto> cadastrar(@RequestBody CadastroClienteDto clienteDto,
            UriComponentsBuilder uri) {
        var cliente = new Cliente(clienteDto);
        clienteRepository.save(cliente);
        var url = uri.path("/cliente/{clienteId}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesClienteDto(cliente));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<DetalhesClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
            @RequestBody AtualizacaoClienteDto clienteDto) throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId).
                orElseThrow(EntityNotFoundException::new);
        cliente.atualizarDados(clienteDto);
        return ResponseEntity.ok(new DetalhesClienteDto(cliente));
    }

    @DeleteMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("clienteId") Long clienteId) throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId).
                orElseThrow(EntityNotFoundException::new);
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

}
