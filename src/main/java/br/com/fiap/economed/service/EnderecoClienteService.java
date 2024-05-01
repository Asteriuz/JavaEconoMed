package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.clienteEndereco.AtualizacaoEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.CadastroEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.DetalhesEnderecoClienteDto;
import br.com.fiap.economed.model.EnderecoCliente;
import br.com.fiap.economed.repository.EnderecoClienteRepository;
import br.com.fiap.economed.service.interfaces.IEnderecoClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoClienteService implements IEnderecoClienteService {

    private final EnderecoClienteRepository enderecoClienteRepository;

    public EnderecoClienteService(EnderecoClienteRepository enderecoClienteRepository) {
        this.enderecoClienteRepository = enderecoClienteRepository;
    }

    @Override
    public DetalhesEnderecoClienteDto buscarEnderecoCliente(Long clienteId) throws EntityNotFoundException {
        var endereco = enderecoClienteRepository.findByClienteId(clienteId)
                .orElseThrow(EntityNotFoundException::new);
        return new DetalhesEnderecoClienteDto(endereco);
    }

    @Override
    public EnderecoCliente cadastrarEnderecoCliente(CadastroEnderecoClienteDto enderecoClienteDto) {
        var endereco = new EnderecoCliente(enderecoClienteDto);
        return enderecoClienteRepository.save(endereco);
    }

    @Override
    public EnderecoCliente atualizarEnderecoCliente(Long clienteId, AtualizacaoEnderecoClienteDto enderecoClienteDto) throws EntityNotFoundException {
        var endereco = enderecoClienteRepository.findById(clienteId)
                .orElseThrow(EntityNotFoundException::new);
        endereco.atualizarDados(enderecoClienteDto);
        return enderecoClienteRepository.save(endereco);
    }
}
