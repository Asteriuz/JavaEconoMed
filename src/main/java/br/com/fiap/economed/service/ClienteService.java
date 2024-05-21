package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.repository.CidadeRepository;
import br.com.fiap.economed.repository.ClienteRepository;
import br.com.fiap.economed.repository.ConvenioRepository;
import br.com.fiap.economed.repository.EstadoCivilRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.fiap.economed.service.interfaces.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private ConvenioRepository convenioRepository;

    @Override
    public Page<DetalhesClienteDTO> listarClientes(Pageable paginacao) {
        return clienteRepository.findAll(paginacao).map(DetalhesClienteDTO::new);
    }

    @Override
    public DetalhesClienteDTO buscarCliente(Long clienteId) throws EntityNotFoundException {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        return (new DetalhesClienteDTO(cliente));
    }

    @Override
    public Cliente cadastrarCliente(CadastroClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);
        var cidade = cidadeRepository.getReferenceById(clienteDTO.endereco().cidadeId());
        cliente.getEndereco().setCidade(cidade);
        var estadoCivil = estadoCivilRepository.getReferenceById(clienteDTO.estadoCivilId());
        cliente.setEstadoCivil(estadoCivil);
        var convenio = convenioRepository.getReferenceById(clienteDTO.convenioId());
        cliente.setConvenio(convenio);
        cliente.getEndereco().setCliente(cliente);
        cliente.getHistoricoHospital().setCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDTO clienteDTO) throws EntityNotFoundException {
        var cliente = clienteRepository.getReferenceById(clienteId);
        cliente.atualizar(clienteDTO);
        return cliente;
    }

    @Override
    public void removerCliente(Long clienteId) throws EntityNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));
        clienteRepository.delete(cliente);
    }
}
