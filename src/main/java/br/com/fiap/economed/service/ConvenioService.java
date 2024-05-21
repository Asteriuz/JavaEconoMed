package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDTO;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDTO;
import br.com.fiap.economed.dto.convenio.DetalhesConvenioDTO;
import br.com.fiap.economed.model.Convenio;
import br.com.fiap.economed.repository.ConvenioRepository;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.service.interfaces.IConvenioService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConvenioService implements IConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Page<DetalhesConvenioDTO> listarConvenio(Pageable paginacao) {
        return convenioRepository.findAll(paginacao).map(DetalhesConvenioDTO::new);
    }

    @Override
    public DetalhesConvenioDTO buscarConvenio(Long convenioId) throws EntityNotFoundException {
        var convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));

        return (new DetalhesConvenioDTO(convenio));
    }

    @Override
    public Convenio cadastrarConvenio(CadastroConvenioDTO convenioDTO) {
        Convenio convenio = new Convenio(convenioDTO);
        var empresa = empresaRepository.getReferenceById(convenioDTO.empresaId());
        convenio.setEmpresa(empresa);
        return convenioRepository.save(convenio);
    }

    @Override
    public Convenio atualizarConvenio(Long convenioId, AtualizacaoConvenioDTO convenioDTO)
            throws EntityNotFoundException {
        Convenio convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));
        convenio.atualizarDados(convenioDTO);
        return convenioRepository.save(convenio);
    }

    @Override
    public void removerConvenio(Long convenioId) throws EntityNotFoundException {
        Convenio convenio = convenioRepository.findById(convenioId)
                .orElseThrow(() -> new EntityNotFoundException("Convenio não encontrado com ID: " + convenioId));
        convenioRepository.delete(convenio);
    }
}
