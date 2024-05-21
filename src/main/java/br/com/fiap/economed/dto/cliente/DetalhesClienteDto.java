package br.com.fiap.economed.dto.cliente;

import br.com.fiap.economed.dto.enderecoCliente.DetalhesEnderecoClienteDTO;
import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDTO;
import br.com.fiap.economed.dto.estadoCivil.DetalhesEstadoCivilDTO;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDTO;
import br.com.fiap.economed.model.Cliente;

import java.time.LocalDate;

public record DetalhesClienteDTO(
        Long id,
        String rg,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String cpf,
        DetalhesConvenioDTO convenio,
        DetalhesEstadoCivilDTO estadoCivil,
        DetalhesEnderecoClienteDTO endereco,
        DetalhesHistoricoHospitalClienteDTO historicoHospital) {

    public DetalhesClienteDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getRg(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                new DetalhesConvenioDTO(cliente.getConvenio()),
                new DetalhesEstadoCivilDTO(cliente.getEstadoCivil()),
                new DetalhesEnderecoClienteDTO(cliente.getEndereco()),
                new DetalhesHistoricoHospitalClienteDTO(cliente.getHistoricoHospital()));
    }
}
