package br.com.fiap.economed.dto.estadoCivil;

import br.com.fiap.economed.model.EstadoCivil;

public record DetalhesEstadoCivilDTO(
        Long id,
        String nome) {

    public DetalhesEstadoCivilDTO(EstadoCivil estadoCivil) {
        this(
                estadoCivil.getId(),
                estadoCivil.getNome());
    }
}