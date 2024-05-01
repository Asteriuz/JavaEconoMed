package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.clienteEndereco.AtualizacaoEnderecoClienteDto;
import br.com.fiap.economed.service.EnderecoClienteService;
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

    private final EnderecoClienteService enderecoService;

    public EnderecoClienteController(EnderecoClienteService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{clienteId}/endereco")
    public ResponseEntity<DetalhesEnderecoClienteDto> search(@PathVariable Long clienteId)
            throws EntityNotFoundException {
        var endereco = enderecoService.buscarEnderecoCliente(clienteId);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping("/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> create(@RequestBody CadastroEnderecoClienteDto enderecoDto,
            UriComponentsBuilder uriBuilder) {
        var endereco = enderecoService.cadastrarEnderecoCliente(enderecoDto);

        return ResponseEntity.created(uriBuilder.path("/clientes/{clienteId}/endereco").buildAndExpand(endereco.getClienteId()).toUri())
                .body(new DetalhesEnderecoClienteDto(endereco));
    }

    @PutMapping("/{clienteId}/endereco")
    @Transactional
    public ResponseEntity<EnderecoCliente> update(@PathVariable Long clienteId,
            @RequestBody AtualizacaoEnderecoClienteDto enderecoDto)
            throws EntityNotFoundException {
        var endereco = enderecoService.atualizarEnderecoCliente(clienteId, enderecoDto);
        return ResponseEntity.ok(endereco);
    }
}
