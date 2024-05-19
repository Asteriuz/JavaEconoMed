package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDTO;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDTO;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDTO;
import br.com.fiap.economed.model.Convenio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConvenioService {
    Page<DetalhesConvenioDTO> listarConvenio(Pageable pageable);
    DetalhesConvenioDTO buscarConvenio(Long convenioId) throws EntityNotFoundException;
    Convenio cadastrarConvenio(CadastroConvenioDTO convenioDTO);
    Convenio atualizarConvenio(Long convenioId, AtualizacaoConvenioDTO convenioDTO) throws EntityNotFoundException;
    void removerConvenio(Long convenioId) throws EntityNotFoundException;

}
