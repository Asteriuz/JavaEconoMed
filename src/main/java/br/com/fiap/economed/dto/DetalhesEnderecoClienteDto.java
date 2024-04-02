package br.com.fiap.economed.dto;

import br.com.fiap.economed.model.EnderecoCliente;

public record DetalhesEnderecoClienteDto(
        Long id,
        Long clienteId,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep) {

    public DetalhesEnderecoClienteDto(EnderecoCliente enderecoCliente) {
        this(
                enderecoCliente.getId(),
                enderecoCliente.getClienteId(),
                enderecoCliente.getRua(),
                enderecoCliente.getNumero(),
                enderecoCliente.getCidade(),
                enderecoCliente.getEstado(),
                enderecoCliente.getCep());
    }
}