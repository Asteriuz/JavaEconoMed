package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.unidadeEndereco.AtualizacaoEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.CadastroEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.DetalhesEnderecoUnidadeDto;
import br.com.fiap.economed.model.EnderecoUnidade;
import br.com.fiap.economed.repository.EnderecoUnidadeRepository;
import br.com.fiap.economed.service.interfaces.IEnderecoUnidadeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoUnidadeService implements IEnderecoUnidadeService {

    private final EnderecoUnidadeRepository enderecoUnidadeRepository;

    public EnderecoUnidadeService(EnderecoUnidadeRepository enderecoUnidadeRepository) {
        this.enderecoUnidadeRepository = enderecoUnidadeRepository;
    }

    @Override
    public DetalhesEnderecoUnidadeDto buscarEnderecoUnidade(Long unidadeId) throws EntityNotFoundException {
        var endereco = enderecoUnidadeRepository.findByUnidadeId(unidadeId)
                .orElseThrow(EntityNotFoundException::new);
        return new DetalhesEnderecoUnidadeDto(endereco);
    }

    @Override
    public EnderecoUnidade cadastrarEnderecoUnidade(CadastroEnderecoUnidadeDto enderecoDto) {
        EnderecoUnidade endereco = new EnderecoUnidade(enderecoDto);
        return enderecoUnidadeRepository.save(endereco);
    }

    @Override
    public EnderecoUnidade atualizarEnderecoUnidade(Long unidadeId, AtualizacaoEnderecoUnidadeDto enderecoDto) throws EntityNotFoundException {
        var endereco = enderecoUnidadeRepository.findByUnidadeId(unidadeId)
                .orElseThrow(EntityNotFoundException::new);
        endereco.atualizarDados(enderecoDto);
        return enderecoUnidadeRepository.save(endereco);
    }
}
