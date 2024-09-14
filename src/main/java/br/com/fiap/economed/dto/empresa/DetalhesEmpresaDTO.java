package br.com.fiap.economed.dto.empresa;

import br.com.fiap.economed.model.Empresa;

public record DetalhesEmpresaDTO(
        Long id,
        String cnpj,
        String nome,
        String tipo,
        String telefone,
        String email) {

    public DetalhesEmpresaDTO(Empresa empresa) {
        this(
                empresa.getId(),
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getTipo(),
                empresa.getTelefone(),
                empresa.getEmail());
    }
}
