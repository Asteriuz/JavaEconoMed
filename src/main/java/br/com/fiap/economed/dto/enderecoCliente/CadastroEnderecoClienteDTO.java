package br.com.fiap.economed.dto.enderecoCliente;

public record CadastroEnderecoClienteDTO(
                String rua,
                String numero,
                String cep,
                Long cidadeId) {
}
