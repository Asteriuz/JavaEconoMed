package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDto;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDto;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDto;
import br.com.fiap.economed.model.Unidade;
import br.com.fiap.economed.repository.UnidadeRepository;
import br.com.fiap.economed.service.interfaces.IUnidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnidadeService implements IUnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public Page<DetalhesUnidadeDto> listarUnidades(Pageable paginacao) {
        return unidadeRepository.findAll(paginacao).map(DetalhesUnidadeDto::new);
    }

    @Override
    public DetalhesUnidadeDto buscarUnidade(Long unidadeId) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));

        return (new DetalhesUnidadeDto(unidade));
    }

    @Override
    public Unidade cadastrarUnidade(CadastroUnidadeDto unidadeDto) {
        Unidade unidade = new Unidade(unidadeDto);
        return unidadeRepository.save(unidade);
    }

    @Override
    public Unidade atualizarUnidade(Long unidadeId, AtualizacaoUnidadeDto unidadeDto) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        unidade.atualizarDados(unidadeDto);
        return unidadeRepository.save(unidade);
    }

    @Override
    public void removerUnidade(Long unidadeId) throws EntityNotFoundException {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        unidadeRepository.delete(unidade);
    }

}
