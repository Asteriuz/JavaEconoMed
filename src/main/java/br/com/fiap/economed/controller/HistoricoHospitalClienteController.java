package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDTO;
import br.com.fiap.economed.repository.HistoricoHospitalClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico-hospital")
public class HistoricoHospitalClienteController {

    @Autowired
    private HistoricoHospitalClienteRepository historicoHospitalClienteRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesHistoricoHospitalClienteDTO>> pesquisar(Pageable pageable) {
        var page = historicoHospitalClienteRepository.findAll(pageable).map(DetalhesHistoricoHospitalClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesHistoricoHospitalClienteDTO> pesquisar(@PathVariable("id") Long id) {
        var endereco = new DetalhesHistoricoHospitalClienteDTO(historicoHospitalClienteRepository.getReferenceById(id));
        return ResponseEntity.ok(endereco);
    }
}
