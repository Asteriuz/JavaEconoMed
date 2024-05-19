package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDTO;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDTO;
import br.com.fiap.economed.dto.unidade.DetalhesUnidadeDTO;
import br.com.fiap.economed.model.Unidade;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.repository.UnidadeRepository;
import br.com.fiap.economed.service.interfaces.IUnidadeService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnidadeService implements IUnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Page<DetalhesUnidadeDTO> listarUnidades(Pageable paginacao) {
        return unidadeRepository.findAll(paginacao).map(DetalhesUnidadeDTO::new);
    }

    @Override
    public DetalhesUnidadeDTO buscarUnidade(Long unidadeId) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));

        return (new DetalhesUnidadeDTO(unidade));
    }

    @Override
    public Unidade cadastrarUnidade(CadastroUnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade(unidadeDTO);
        var empresa = empresaRepository.getReferenceById(unidadeDTO.empresaId());
        unidade.setEmpresa(empresa);
        return unidadeRepository.save(unidade);
    }

    @Override
    public Unidade atualizarUnidade(Long unidadeId, AtualizacaoUnidadeDTO unidadeDTO) throws EntityNotFoundException {
        var unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        unidade.atualizarDados(unidadeDTO);
        return unidadeRepository.save(unidade);
    }

    @Override
    public void removerUnidade(Long unidadeId) throws EntityNotFoundException {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + unidadeId));
        unidadeRepository.delete(unidade);
    }

}
