package br.com.fiap.economed.dto.unidade;

public record AtualizacaoUnidadeDTO(
                Long empresaId,
                String nome,
                String telefone,
                String email,
                String tipo,
                Integer capacidade) {
}
