package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.economed.model.Cliente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {
    Page<DetalhesClienteDTO> listarClientes(Pageable paginacao);
    DetalhesClienteDTO buscarCliente(Long clienteId) throws EntityNotFoundException;
    Cliente cadastrarCliente(CadastroClienteDTO clienteDTO);
    Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDTO clienteDTO) throws EntityNotFoundException;
    void removerCliente(Long clienteId) throws EntityNotFoundException;
}
