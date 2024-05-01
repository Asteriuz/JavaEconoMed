package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDto;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.economed.model.Empresa;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmpresaService {
    Page<DetalhesEmpresaDto> listarEmpresas(Pageable paginacao);
    DetalhesEmpresaDto buscarEmpresa(Long empresaId) throws EntityNotFoundException;
    Empresa cadastrarEmpresa(CadastroEmpresaDto empresaDto);
    Empresa atualizarEmpresa(Long empresaId, AtualizacaoEmpresaDto empresaDto) throws EntityNotFoundException;
    void removerEmpresa(Long empresaId) throws EntityNotFoundException;
}
