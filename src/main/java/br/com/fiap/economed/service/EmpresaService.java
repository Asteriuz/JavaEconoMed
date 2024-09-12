package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.model.User;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.repository.UserRepository;
import br.com.fiap.economed.service.interfaces.IEmpresaService;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private final EmpresaRepository empresaRepository;

    @Autowired
    private final UserRepository userRepository;

    public EmpresaService(EmpresaRepository empresaRepository, UserRepository userRepository) {
        this.empresaRepository = empresaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<DetalhesEmpresaDTO> listarEmpresas(Pageable paginacao) {
        return empresaRepository.findAll(paginacao).map(DetalhesEmpresaDTO::new);
    }

    @Override
    public DetalhesEmpresaDTO buscarEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));

        return new DetalhesEmpresaDTO(empresa);
    }

    @Override
    @Transactional
    public Empresa cadastrarEmpresa(CadastroEmpresaDTO empresaDTO) {
        User user = (User) userRepository.findByLogin(empresaDTO.getLogin());

        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado com login: " + empresaDTO.getLogin());
        }

        Empresa empresa = new Empresa(empresaDTO);

        empresa.setId(user.getId());

        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional
    public Empresa atualizarEmpresa(Long empresaId, AtualizacaoEmpresaDTO empresaDTO) {
        Empresa empresa = empresaRepository.getReferenceById(empresaId);
        empresa.atualizar(empresaDTO);
        empresaRepository.save(empresa);
        if (empresa.getUser() != null) {
            userRepository.save(empresa.getUser());
        }

        return empresa;
    }

    @Override
    @Transactional
    public void removerEmpresa(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));

        User user = empresa.getUser();

        empresaRepository.delete(empresa);

        if (user != null) {
            userRepository.delete(user);
        }
    }
}
