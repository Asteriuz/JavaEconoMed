package br.com.fiap.economed.dto;

public record CadastroEnderecoClienteDto(
                Long id,
                Long clienteId,
                String rua,
                String numero,
                String cidade,
                String estado,
                String cep) {
}
