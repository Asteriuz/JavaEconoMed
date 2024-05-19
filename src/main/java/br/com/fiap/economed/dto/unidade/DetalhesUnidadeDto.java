package br.com.fiap.economed.dto.unidade;

import br.com.fiap.economed.model.Unidade;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;

public record DetalhesUnidadeDTO(
        Long id,
        DetalhesEmpresaDTO empresa,
        String nome,
        String telefone,
        String email,
        String tipo,
        Integer capacidade) {

    public DetalhesUnidadeDTO(Unidade unidade) {
        this(
                unidade.getId(),
                new DetalhesEmpresaDTO(unidade.getEmpresa()),
                unidade.getNome(),
                unidade.getTelefone(),
                unidade.getEmail(),
                unidade.getTipo(),
                unidade.getCapacidade());
    }
}