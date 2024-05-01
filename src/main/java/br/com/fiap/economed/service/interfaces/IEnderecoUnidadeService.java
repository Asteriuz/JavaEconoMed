package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.unidadeEndereco.AtualizacaoEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.CadastroEnderecoUnidadeDto;
import br.com.fiap.economed.dto.unidadeEndereco.DetalhesEnderecoUnidadeDto;
import br.com.fiap.economed.model.EnderecoUnidade;
import jakarta.persistence.EntityNotFoundException;

public interface IEnderecoUnidadeService {
    DetalhesEnderecoUnidadeDto buscarEnderecoUnidade(Long unidadeId) throws EntityNotFoundException;
    EnderecoUnidade cadastrarEnderecoUnidade(CadastroEnderecoUnidadeDto enderecoDto);
    EnderecoUnidade atualizarEnderecoUnidade(Long unidadeId, AtualizacaoEnderecoUnidadeDto enderecoDto) throws EntityNotFoundException;
}
