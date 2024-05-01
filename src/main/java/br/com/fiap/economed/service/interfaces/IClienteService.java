package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.dto.cliente.CadastroClienteDto;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.model.Cliente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    Page<DetalhesClienteDto> listarClientes(Pageable paginacao);
    DetalhesClienteDto buscarCliente(Long clienteId) throws EntityNotFoundException;
    Cliente cadastrarCliente(CadastroClienteDto clienteDto);
    Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDto clienteDto) throws EntityNotFoundException;
    void removerCliente(Long clienteId) throws EntityNotFoundException;
}
