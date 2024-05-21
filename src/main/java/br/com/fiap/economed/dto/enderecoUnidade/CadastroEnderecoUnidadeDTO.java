package br.com.fiap.economed.dto.enderecoUnidade;

public record CadastroEnderecoUnidadeDTO(
                String rua,
                String numero,
                String cep,
                Long cidadeId) {
}
