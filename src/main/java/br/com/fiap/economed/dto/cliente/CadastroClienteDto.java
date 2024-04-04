package br.com.fiap.economed.dto.cliente;


import java.time.LocalDate;

import br.com.fiap.economed.model.enums.EstadoCivilCliente;

public record CadastroClienteDto(
        String rg,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String cpf,
        EstadoCivilCliente estadoCivil,
        Long convenioId) {
}
