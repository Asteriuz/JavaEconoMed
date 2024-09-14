package br.com.fiap.economed.dto.cliente;

import java.time.LocalDate;

import br.com.fiap.economed.dto.enderecoCliente.CadastroEnderecoClienteDTO;
import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDTO;

public record CadastroClienteDTO(
                String rg,
                String nome,
                String telefone,
                String email,
                LocalDate dataNascimento,
                String cpf,
                Long convenioId,
                Long estadoCivilId,
                CadastroEnderecoClienteDTO endereco,
                CadastroHistoricoHospitalClienteDTO historicoHospital) {

    public String getLogin() {
        return cpf;
    }

}
