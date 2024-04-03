package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.clienteEndereco.AtualizacaoEnderecoClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.clienteEndereco.CadastroEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.DetalhesEnderecoClienteDto;
import br.com.fiap.economed.model.EnderecoCliente;
import br.com.fiap.economed.repository.EnderecoClienteRepository;

@RestController
@RequestMapping("/endereco-cliente")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository enderecoRepository;

    //TODO: Implementar paginaçâo e ordenação, criar statusCode exceptions

    @GetMapping("/{clienteId}")
    public ResponseEntity<DetalhesEnderecoClienteDto> buscar(@PathVariable("clienteId") Long clienteId) throws ChangeSetPersister.NotFoundException {
        var endereco = enderecoRepository.findById(clienteId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return ResponseEntity.ok(new DetalhesEnderecoClienteDto(endereco));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> cadastrar(@RequestBody CadastroEnderecoClienteDto enderecoDto,
            UriComponentsBuilder uriBuilder) {
        var endereco = new EnderecoCliente(enderecoDto);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("/endereco-cliente/{clienteId}").buildAndExpand(endereco.getClienteId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoClienteDto(endereco));
    }

    @PutMapping("/{clienteId}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> atualizar(@PathVariable("clienteId") Long clienteId,
            @RequestBody AtualizacaoEnderecoClienteDto enderecoDto) throws ChangeSetPersister.NotFoundException {
        var endereco = enderecoRepository.findById(clienteId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        endereco.atualizarDados(enderecoDto);
        return ResponseEntity.ok(new DetalhesEnderecoClienteDto(endereco));
    }
}
