package br.com.fiap.economed.mapper;

import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public DetalhesClienteDto toDetalhesClienteDto(Cliente cliente) {
        return new DetalhesClienteDto(
                cliente.getId(),
                cliente.getRg(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                cliente.getEstadoCivil(),
                cliente.getConvenioId()
        );
    }
}
