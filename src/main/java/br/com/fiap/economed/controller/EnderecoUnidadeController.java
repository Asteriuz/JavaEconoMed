package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.unidadeEndereco.AtualizacaoEnderecoUnidadeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.unidadeEndereco.CadastroEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.DetalhesEnderecoUnidadeDto;
import br.com.fiap.economed.model.EnderecoUnidade;
import br.com.fiap.economed.repository.EnderecoUnidadeRepository;

@RestController
@RequestMapping("/endereco-unidade")
public class EnderecoUnidadeController {

    @Autowired
    private EnderecoUnidadeRepository enderecoRepository;

    //TODO: Implementar paginaçâo e ordenação, criar statusCode exceptions

    @GetMapping("/{unidadeId}")
    public ResponseEntity<DetalhesEnderecoUnidadeDto> buscar(@PathVariable("unidadeId") Long unidadeId) throws ChangeSetPersister.NotFoundException {
        var endereco = enderecoRepository.findById(unidadeId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return ResponseEntity.ok(new DetalhesEnderecoUnidadeDto(endereco));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoUnidadeDto> cadastrar(@RequestBody CadastroEnderecoUnidadeDto enderecoDto,
            UriComponentsBuilder uriBuilder) {
        var endereco = new EnderecoUnidade(enderecoDto);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("/endereco-unidade/{unidadeId}").buildAndExpand(endereco.getUnidadeId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoUnidadeDto(endereco));
    }

    @PutMapping("/{unidadeId}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoUnidadeDto> atualizar(@PathVariable("unidadeId") Long unidadeId,
            @RequestBody AtualizacaoEnderecoUnidadeDto enderecoDto) throws ChangeSetPersister.NotFoundException {
        var endereco = enderecoRepository.findById(unidadeId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        endereco.atualizarDados(enderecoDto);
        return ResponseEntity.ok(new DetalhesEnderecoUnidadeDto(endereco));
    }
}
