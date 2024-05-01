package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDto;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDto;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDto;
import br.com.fiap.economed.model.Convenio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConvenioService {
    Page<DetalhesConvenioDto> listarConvenio(Pageable pageable);
    DetalhesConvenioDto buscarConvenio(Long convenioId) throws EntityNotFoundException;
    Convenio cadastrarConvenio(CadastroConvenioDto convenioDto);
    Convenio atualizarConvenio(Long convenioId, AtualizacaoConvenioDto convenioDto) throws EntityNotFoundException;
    void removerConvenio(Long convenioId) throws EntityNotFoundException;

}
