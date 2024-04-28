package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import br.com.fiap.economed.dto.cliente.CadastroClienteDto;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.fiap.economed.service.interfaces.IClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long clienteId) throws EntityNotFoundException {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));
    }

    @Override
    public Cliente cadastrarCliente(CadastroClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDto clienteDto) throws EntityNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
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
