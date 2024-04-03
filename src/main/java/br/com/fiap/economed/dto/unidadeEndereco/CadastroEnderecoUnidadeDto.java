package br.com.fiap.economed.dto.unidadeEndereco;

public record CadastroEnderecoUnidadeDto(
                Long unidadeId,
                String rua,
                String numero,
                String cidade,
                String estado,
                String cep) {
}
