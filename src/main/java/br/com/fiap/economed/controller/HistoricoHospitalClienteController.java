package br.com.fiap.economed.controller;

import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDTO;
import br.com.fiap.economed.repository.HistoricoHospitalClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/historico-hospital")
@Tag(name = "Histórico dos Hospitais")
public class HistoricoHospitalClienteController {

    @Autowired
    private HistoricoHospitalClienteRepository historicoHospitalClienteRepository;

    @GetMapping
    @Operation(summary = "Listar histórico de hospitais", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<Page<DetalhesHistoricoHospitalClienteDTO>> pesquisar(
            @ParameterObject @Parameter(description = "Paginação dos resultados (número da página, tamanho, ordenação)", schema = @Schema(implementation = Pageable.class, defaultValue = "{ \"page\": 0, \"size\": 10, \"sort\": \"id,asc\" }")) Pageable pageable) {
        log.info("Listando histórico de hospitais");
        var page = historicoHospitalClienteRepository.findAll(pageable).map(DetalhesHistoricoHospitalClienteDTO::new);
        log.info("Listagem de histórico de hospitais finalizada com o seguinte resultado: {}", page);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar histórico de hospital", security = @SecurityRequirement(name = "jwtBearerAuth"))
    public ResponseEntity<DetalhesHistoricoHospitalClienteDTO> pesquisar(@PathVariable("id") Long id) {
        log.info("Buscando histórico de hospital com ID: {}", id);
        var endereco = new DetalhesHistoricoHospitalClienteDTO(historicoHospitalClienteRepository.getReferenceById(id));
        log.info("Histórico de hospital encontrado: {}", endereco);
        return ResponseEntity.ok(endereco);
    }
}
