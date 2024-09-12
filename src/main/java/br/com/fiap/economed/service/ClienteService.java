package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;
import br.com.fiap.economed.dto.cliente.DetalhesClienteDTO;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.model.User;
import br.com.fiap.economed.repository.*;
import jakarta.persistence.EntityNotFoundException;
import br.com.fiap.economed.service.interfaces.IClienteService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final CidadeRepository cidadeRepository;

    @Autowired
    private final EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private final ConvenioRepository convenioRepository;

    @Autowired
    private final UserRepository userRepository;

    public ClienteService(ClienteRepository clienteRepository,
            CidadeRepository cidadeRepository,
            EstadoCivilRepository estadoCivilRepository,
            ConvenioRepository convenioRepository,
            UserRepository userRepository) {
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.convenioRepository = convenioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<DetalhesClienteDTO> listarClientes(Pageable paginacao) {
        return clienteRepository.findAll(paginacao).map(DetalhesClienteDTO::new);
    }

    @Override
    public DetalhesClienteDTO buscarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        return new DetalhesClienteDTO(cliente);
    }

    @Override
    @Transactional
    public Cliente cadastrarCliente(CadastroClienteDTO clienteDTO) {
        User user = (User) userRepository.findByLogin(clienteDTO.getLogin());

        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado com login: " + clienteDTO.getLogin());
        }

        Cliente cliente = new Cliente(clienteDTO);

        var cidade = cidadeRepository.getReferenceById(clienteDTO.endereco().cidadeId());
        var estadoCivil = estadoCivilRepository.getReferenceById(clienteDTO.estadoCivilId());
        var convenio = convenioRepository.getReferenceById(clienteDTO.convenioId());

        cliente.setId(user.getId());
        cliente.getEndereco().setCidade(cidade);
        cliente.setEstadoCivil(estadoCivil);
        cliente.setConvenio(convenio);
        cliente.getEndereco().setCliente(cliente);
        cliente.getHistoricoHospital().setCliente(cliente);

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente atualizarCliente(Long clienteId, AtualizacaoClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.getReferenceById(clienteId);
        cliente.atualizar(clienteDTO);
        clienteRepository.save(cliente);
        if (cliente.getUser() != null) {
            userRepository.save(cliente.getUser());
        }

        return cliente;
    }

    @Override
    @Transactional
    public void removerCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));

        User user = cliente.getUser();

        clienteRepository.delete(cliente);

        if (user != null) {
            userRepository.delete(user);
        }
    }
}
