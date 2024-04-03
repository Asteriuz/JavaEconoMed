package br.com.fiap.economed.dto.unidade;

import br.com.fiap.economed.model.Unidade;

public record DetalhesUnidadeDto(
        Long id,
        Long empresaId,
        String nome,
        String telefone,
        String email,
        String tipo,
        Integer capacidade,
        String especialidades) {

    public DetalhesUnidadeDto(Unidade unidade) {
        this(
                unidade.getId(),
                unidade.getEmpresaId(),
                unidade.getNome(),
                unidade.getTelefone(),
                unidade.getEmail(),
                unidade.getTipo(),
                unidade.getCapacidade(),
                unidade.getEspecialidades());
    }
}