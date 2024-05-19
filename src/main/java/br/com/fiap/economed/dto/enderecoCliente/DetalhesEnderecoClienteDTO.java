package br.com.fiap.economed.dto.enderecoCliente;

import br.com.fiap.economed.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.economed.model.EnderecoCliente;

public record DetalhesEnderecoClienteDTO(
        Long id,
        String rua,
        String numero,
        String cep,
        DetalhesCidadeDTO cidade) {

    public DetalhesEnderecoClienteDTO(EnderecoCliente enderecoCliente) {
        this(
                enderecoCliente.getId(),
                enderecoCliente.getRua(),
                enderecoCliente.getNumero(),
                enderecoCliente.getCep(),
                new DetalhesCidadeDTO(enderecoCliente.getCidade()));
    }
}