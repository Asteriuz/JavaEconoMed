package br.com.fiap.economed.dto.empresa;

public record AtualizacaoEmpresaDTO(
        String cnpj,
        String nome,
        String tipo,
        String telefone,
        String email) {
}
