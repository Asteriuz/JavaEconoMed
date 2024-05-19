package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.service.interfaces.IEmpresaService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Page<DetalhesEmpresaDTO> listarEmpresas(Pageable paginacao) {
        return empresaRepository.findAll(paginacao).map(DetalhesEmpresaDTO::new);
    }

    @Override
    public DetalhesEmpresaDTO buscarEmpresa(Long empresaId) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));

        return (new DetalhesEmpresaDTO(empresa));
    }

    @Override
    public Empresa cadastrarEmpresa(CadastroEmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa(empresaDTO);
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa atualizarEmpresa(Long empresaId, AtualizacaoEmpresaDTO empresaDTO) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));
        empresa.atualizarDados(empresaDTO);
        return empresaRepository.save(empresa);
    }

    @Override
    public void removerEmpresa(Long empresaId) throws EntityNotFoundException {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));
        empresaRepository.delete(empresa);
    }
}
