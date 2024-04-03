package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.CadastroEnderecoClienteDto;
import br.com.fiap.economed.dto.DetalhesEnderecoClienteDto;
import br.com.fiap.economed.model.EnderecoCliente;
import br.com.fiap.economed.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco-cliente")
public class EnderecoClienteController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDto> cadastrar(
            @RequestBody CadastroEnderecoClienteDto enderecoClienteDto,
            UriComponentsBuilder uriBuilder) {
        var enderecoCliente = new EnderecoCliente(enderecoClienteDto);
        enderecoRepository.save(enderecoCliente);
        var url = uriBuilder.path("endereco_cliente/{codigo}").buildAndExpand(enderecoCliente.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEnderecoClienteDto(enderecoCliente));
    }
}
