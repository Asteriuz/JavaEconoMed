package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.unidadeEndereco.AtualizacaoEnderecoUnidadeDto;
import br.com.fiap.economed.service.EnderecoUnidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.unidadeEndereco.CadastroEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.DetalhesEnderecoUnidadeDto;
import br.com.fiap.economed.model.EnderecoUnidade;
import br.com.fiap.economed.repository.EnderecoUnidadeRepository;

@RestController
@RequestMapping("/unidades")
public class EnderecoUnidadeController {

    private final EnderecoUnidadeService enderecoService;

    public EnderecoUnidadeController(EnderecoUnidadeService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{unidadeId}/endereco")
    public ResponseEntity<DetalhesEnderecoUnidadeDto> find(@PathVariable("unidadeId") Long unidadeId)
            throws EntityNotFoundException {
        var endereco = enderecoService.buscarEnderecoUnidade(unidadeId);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping("/endereco")
    @Transactional
    public ResponseEntity<EnderecoUnidade> create(@RequestBody CadastroEnderecoUnidadeDto enderecoDto,
            UriComponentsBuilder uriBuilder) {
        var endereco = enderecoService.cadastrarEnderecoUnidade(enderecoDto);

        return ResponseEntity.created(uriBuilder.path("/unidades/{unidadeId}/endereco").buildAndExpand(endereco.getUnidadeId()).toUri())
                .body(endereco);
    }

    @PutMapping("/{unidadeId}/endereco")
    @Transactional
    public ResponseEntity<EnderecoUnidade> update(@PathVariable("unidadeId") Long unidadeId,
            @RequestBody AtualizacaoEnderecoUnidadeDto enderecoDto) throws EntityNotFoundException {
        var endereco = enderecoService.atualizarEnderecoUnidade(unidadeId, enderecoDto);
        return ResponseEntity.ok(endereco);
    }
}
