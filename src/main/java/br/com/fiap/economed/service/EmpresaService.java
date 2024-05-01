package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDto;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.service.interfaces.IEmpresaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EmpresaService implements IEmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Page<DetalhesEmpresaDto> listarEmpresas(Pageable paginacao) {
        return empresaRepository.findAll(paginacao).map(DetalhesEmpresaDto::new);
    }

    @Override
    public DetalhesEmpresaDto buscarEmpresa(Long empresaId) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));

        return (new DetalhesEmpresaDto(empresa));
    }

    @Override
    public Empresa cadastrarEmpresa(CadastroEmpresaDto empresaDto) {
        Empresa empresa = new Empresa(empresaDto);
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa atualizarEmpresa(Long empresaId, AtualizacaoEmpresaDto empresaDto) throws EntityNotFoundException {
        var empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));
        empresa.atualizarDados(empresaDto);
        return empresaRepository.save(empresa);
    }

    @Override
    public void removerEmpresa(Long empresaId) throws EntityNotFoundException {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com ID: " + empresaId));
        empresaRepository.delete(empresa);
    }
}
