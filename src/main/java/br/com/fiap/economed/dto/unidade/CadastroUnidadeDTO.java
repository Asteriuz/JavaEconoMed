package br.com.fiap.economed.dto.unidade;

public record CadastroUnidadeDTO(
                Long empresaId,
                String nome,
                String telefone,
                String email,
                String tipo,
                Integer capacidade) {

}
