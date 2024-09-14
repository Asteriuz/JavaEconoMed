package br.com.fiap.economed.dto.convenio;

import java.time.LocalDate;

public record CadastroConvenioDTO(
                String nome,
                Long empresaId,
                Double valor,
                String tipoServico,
                String cobertura,
                String contato,
                LocalDate validade) {

}
