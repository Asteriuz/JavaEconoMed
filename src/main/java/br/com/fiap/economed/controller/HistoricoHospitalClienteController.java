package br.com.fiap.economed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.economed.dto.CadastroHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.DetalhesHistoricoHospitalClienteDto;
import br.com.fiap.economed.model.HistoricoHospitalCliente;
import br.com.fiap.economed.repository.HistoricoHospitalClienteRepository;

@RestController
@RequestMapping("/historico-hospital-cliente")
public class HistoricoHospitalClienteController {

    @Autowired
    private HistoricoHospitalClienteRepository historicoSaudeClienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesHistoricoHospitalClienteDto> cadastrar(
            @RequestBody CadastroHistoricoHospitalClienteDto historicoHospitalClienteDto,
            UriComponentsBuilder uriBuilder) {
        var historicoHospitalCliente = new HistoricoHospitalCliente(historicoHospitalClienteDto);
        historicoSaudeClienteRepository.save(historicoHospitalCliente);
        var url = uriBuilder.path("historico_saude_cliente/{codigo}").buildAndExpand(historicoHospitalCliente.getId())
                .toUri();
        return ResponseEntity.created(url).body(new DetalhesHistoricoHospitalClienteDto(historicoHospitalCliente));
    }

}
