package br.com.fiap.economed.dto.unidadeEndereco;

import br.com.fiap.economed.model.EnderecoUnidade;



public record DetalhesEnderecoUnidadeDto(
        Long id,
        Long unidadeId,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep) {

    public DetalhesEnderecoUnidadeDto(EnderecoUnidade enderecoUnidade) {
        this(
                enderecoUnidade.getId(),
                enderecoUnidade.getUnidadeId(),
                enderecoUnidade.getRua(),
                enderecoUnidade.getNumero(),
                enderecoUnidade.getCidade(),
                enderecoUnidade.getEstado(),
                enderecoUnidade.getCep());
    }
}