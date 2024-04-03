package br.com.fiap.economed.dto.unidadeEndereco;

public record AtualizacaoEnderecoUnidadeDto(
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep) {
}
