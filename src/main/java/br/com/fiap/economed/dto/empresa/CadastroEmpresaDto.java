package br.com.fiap.economed.dto.empresa;

public record CadastroEmpresaDTO(
                String cnpj,
                String nome,
                String tipo,
                String telefone,
                String email) {

    public String getLogin() { return cnpj; }

}
