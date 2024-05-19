package br.com.fiap.economed.dto.convenio;

import java.time.LocalDate;

public record AtualizacaoConvenioDTO(
        String nome,
        Double valor,
        String tipoServico,
        String cobertura,
        String contato,
        LocalDate validade) {
}
