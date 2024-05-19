package br.com.fiap.economed.dto.cliente;

import java.time.LocalDate;


public record AtualizacaoClienteDTO(
                String rg,
                String nome,
                String telefone,
                String email,
                LocalDate dataNascimento,
                String cpf,
                Long convenioId
) {
}
