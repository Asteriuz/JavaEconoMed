package br.com.fiap.economed.dto.cliente;

import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.model.enums.EstadoCivilCliente;

import java.time.LocalDate;

public record DetalhesClienteDto(
        Long id,
        String rg,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String cpf,
        EstadoCivilCliente estadoCivil,
        Long convenioId) {

    public DetalhesClienteDto(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getRg(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                cliente.getEstadoCivil(),
                cliente.getConvenioId());
    }
}
