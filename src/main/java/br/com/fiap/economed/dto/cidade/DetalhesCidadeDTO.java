package br.com.fiap.economed.dto.cidade;

import br.com.fiap.economed.dto.estado.DetalhesEstadoDTO;
import br.com.fiap.economed.model.Cidade;

public record DetalhesCidadeDTO(
        Long id,
        String nome,
        DetalhesEstadoDTO estado) {

    public DetalhesCidadeDTO(Cidade cidade) {
        this(
                cidade.getId(),
                cidade.getNome(),
                new DetalhesEstadoDTO(cidade.getEstado()));
    }

}