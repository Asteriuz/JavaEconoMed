package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.dto.cliente.CadastroClienteDto;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.mapper.ClienteMapper;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.fiap.economed.service.interfaces.IClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Page<DetalhesClienteDto> listarClientes(Pageable paginacao) {
        return clienteRepository.findAll(paginacao).map(DetalhesClienteDto::new);
    }

    @Override
    public DetalhesClienteDto buscarCliente(Long clienteId) throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        return (new DetalhesClienteDto(cliente));
    }

    @Override
    public Cliente cadastrarCliente(CadastroClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDto clienteDto) throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));
        cliente.atualizarDados(clienteDto);
        return clienteRepository.save(cliente);
    }

    @Override
    public void removerCliente(Long clienteId) throws EntityNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));
        clienteRepository.delete(cliente);
    }
}
