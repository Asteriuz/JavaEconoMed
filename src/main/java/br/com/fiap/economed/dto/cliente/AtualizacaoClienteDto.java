package br.com.fiap.economed.dto.cliente;

import java.time.LocalDate;

public record AtualizacaoClienteDto(
        String rg,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String cpf,
        String estadoCivil,
        String temConvenio) {
}
