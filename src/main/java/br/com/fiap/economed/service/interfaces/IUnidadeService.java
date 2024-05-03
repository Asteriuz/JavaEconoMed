package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDto;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDto;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDto;
import br.com.fiap.economed.model.Unidade;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUnidadeService {
    Page<DetalhesUnidadeDto> listarUnidades(Pageable paginacao);
    DetalhesUnidadeDto buscarUnidade(Long unidadeId) throws EntityNotFoundException;
    Unidade cadastrarUnidade(CadastroUnidadeDto unidadeDto);
    Unidade atualizarUnidade(Long unidadeId, AtualizacaoUnidadeDto unidadeDto) throws EntityNotFoundException;
    void removerUnidade(Long unidadeId) throws EntityNotFoundException;
}
