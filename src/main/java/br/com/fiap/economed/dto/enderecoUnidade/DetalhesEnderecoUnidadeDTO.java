package br.com.fiap.economed.dto.enderecoUnidade;

import br.com.fiap.economed.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.economed.model.EnderecoUnidade;

public record DetalhesEnderecoUnidadeDTO(
        Long id,
        String rua,
        String numero,
        String cep,
        DetalhesCidadeDTO cidade) {

    public DetalhesEnderecoUnidadeDTO(EnderecoUnidade enderecoUnidade) {
        this(
                enderecoUnidade.getId(),
                enderecoUnidade.getRua(),
                enderecoUnidade.getNumero(),
                enderecoUnidade.getCep(),
                new DetalhesCidadeDTO(enderecoUnidade.getCidade()));
    }
}