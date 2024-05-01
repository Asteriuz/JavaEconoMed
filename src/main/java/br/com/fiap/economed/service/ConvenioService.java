package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.cliente.DetalhesClienteDto;
import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDto;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDto;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDto;
import br.com.fiap.economed.model.Convenio;
import br.com.fiap.economed.repository.ConvenioRepository;
import br.com.fiap.economed.service.interfaces.IConvenioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService implements IConvenioService {

    private final ConvenioRepository convenioRepository;

    public ConvenioService(ConvenioRepository convenioRepository) {
        this.convenioRepository = convenioRepository;
    }
    
    @Override
    public Page<DetalhesConvenioDto> listarConvenio(Pageable paginacao) {
        return convenioRepository.findAll(paginacao).map(DetalhesConvenioDto::new);
    }

    @Override
    public DetalhesConvenioDto buscarConvenio(Long convenioId) throws EntityNotFoundException {
        var convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));

        return (new DetalhesConvenioDto(convenio));
    }

    @Override
    public Convenio cadastrarConvenio(CadastroConvenioDto convenioDto) {
        Convenio convenio = new Convenio(convenioDto);
        return convenioRepository.save(convenio);
    }

    @Override
    public Convenio atualizarConvenio(Long convenioId, AtualizacaoConvenioDto convenioDto) throws EntityNotFoundException {
        Convenio convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));
        convenio.atualizarDados(convenioDto);
        return convenioRepository.save(convenio);
    }

    @Override
    public void removerConvenio(Long convenioId) throws EntityNotFoundException {
        Convenio convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));
        convenioRepository.delete(convenio);
    }
}
