package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.economed.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.economed.model.Empresa;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmpresaService {
    Page<DetalhesEmpresaDTO> listarEmpresas(Pageable paginacao);
    DetalhesEmpresaDTO buscarEmpresa(Long empresaId) throws EntityNotFoundException;
    Empresa cadastrarEmpresa(CadastroEmpresaDTO empresaDTO);
    Empresa atualizarEmpresa(Long empresaId, AtualizacaoEmpresaDTO empresaDTO) throws EntityNotFoundException;
    void removerEmpresa(Long empresaId) throws EntityNotFoundException;
}
