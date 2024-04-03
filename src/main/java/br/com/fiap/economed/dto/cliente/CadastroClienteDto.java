package br.com.fiap.economed.dto.cliente;

import br.com.fiap.economed.model.enums.EstadoCivilCliente;

import java.time.LocalDate;

public record CadastroClienteDto(
        String rg,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String cpf,
        String estadoCivil,
        Long convenioId) {
}
