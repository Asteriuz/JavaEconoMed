package br.com.fiap.economed.dto;

import java.time.LocalDate;

// CREATE TABLE CLIENTE (
//     ID NUMBER PRIMARY KEY,
//     RG VARCHAR(20),
//     NOME VARCHAR(100),
//     TELEFONE VARCHAR(20),
//     EMAIL VARCHAR(100),
//     DATA_NASCIMENTO DATE,
//     CPF VARCHAR(20),
//     ESTADO_CIVIL VARCHAR(20),
//     TEM_CONVENIO CHAR(1)
// );

public record CadastroClienteDto(
                String rg,
                String nome,
                String telefone,
                String email,
                LocalDate dataNascimento,
                String cpf,
                String estadoCivil,
                String temConvenio) {
}
