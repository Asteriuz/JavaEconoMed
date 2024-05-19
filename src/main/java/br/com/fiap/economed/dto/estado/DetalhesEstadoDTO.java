package br.com.fiap.economed.dto.estado;

import br.com.fiap.economed.model.Estado;

public record DetalhesEstadoDTO(
        Long id,
        String nome) {

    public DetalhesEstadoDTO(Estado estado) {
        this(
                estado.getId(),
                estado.getNome());
    }
}