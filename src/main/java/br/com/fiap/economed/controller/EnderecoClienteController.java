package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.clienteEndereco.AtualizacaoEnderecoClienteDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.clienteEndereco.CadastroEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.DetalhesEnderecoClienteDto;
import br.com.fiap.economed.model.EnderecoCliente;
import br.com.fiap.economed.repository.EnderecoClienteRepository;

@RestController
@RequestMapping("/clientes")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository enderecoRepository;

    @GetMapping("/{clienteId}/endereco")
    public ResponseEntity<DetalhesEnderecoClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws EntityNotFoundException {
        var endereco = enderecoRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new DetalhesEnderecoClienteDto(endereco));
    }

    @PostMapping("/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> cadastrar(@RequestBody CadastroEnderecoClienteDto enderecoDto,
            UriComponentsBuilder uriBuilder) {
        var endereco = new EnderecoCliente(enderecoDto);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("clientes/{clienteId}/endereco").buildAndExpand(endereco.getClienteId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoClienteDto(endereco));
    }

    @PutMapping("/{clienteId}/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
            @RequestBody AtualizacaoEnderecoClienteDto enderecoDto) throws EntityNotFoundException {
        var endereco = enderecoRepository.findById(clienteId).
                orElseThrow(EntityNotFoundException::new);
        endereco.atualizarDados(enderecoDto);
        return ResponseEntity.ok(new DetalhesEnderecoClienteDto(endereco));
    }
}
