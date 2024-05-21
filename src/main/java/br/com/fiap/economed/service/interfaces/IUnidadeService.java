package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDTO;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDTO;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDTO;
import br.com.fiap.economed.model.Unidade;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUnidadeService {
    Page<DetalhesUnidadeDTO> listarUnidades(Pageable paginacao);
    DetalhesUnidadeDTO buscarUnidade(Long unidadeId) throws EntityNotFoundException;
    Unidade cadastrarUnidade(CadastroUnidadeDTO unidadeDTO);
    Unidade atualizarUnidade(Long unidadeId, AtualizacaoUnidadeDTO unidadeDTO) throws EntityNotFoundException;
    void removerUnidade(Long unidadeId) throws EntityNotFoundException;
}
