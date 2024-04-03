package br.com.fiap.economed.dto.empresa;


public record CadastroEmpresaDto(
        String cnpj,
        String nome,
        String tipo,
        String telefone,
        String email,
        String areaAtuacao) {

}
