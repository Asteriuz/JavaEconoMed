package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.dto.cliente.CadastroClienteDto;
import br.com.fiap.economed.model.Cliente;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface IClienteService {
    List<Cliente> listarClientes();
    Cliente buscarCliente(Long clienteId) throws EntityNotFoundException;
    Cliente cadastrarCliente(CadastroClienteDto clienteDto);
    Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDto clienteDto) throws EntityNotFoundException;
    void removerCliente(Long clienteId) throws EntityNotFoundException;
}
