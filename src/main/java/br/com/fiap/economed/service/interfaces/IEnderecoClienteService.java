package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.clienteEndereco.AtualizacaoEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.CadastroEnderecoClienteDto;
import br.com.fiap.economed.dto.clienteEndereco.DetalhesEnderecoClienteDto;
import br.com.fiap.economed.model.EnderecoCliente;
import jakarta.persistence.EntityNotFoundException;

public interface IEnderecoClienteService {
    DetalhesEnderecoClienteDto buscarEnderecoCliente(Long clienteId) throws EntityNotFoundException;
    EnderecoCliente cadastrarEnderecoCliente(CadastroEnderecoClienteDto enderecoClienteDto);
    EnderecoCliente atualizarEnderecoCliente(Long clienteId, AtualizacaoEnderecoClienteDto enderecoClienteDto) throws EntityNotFoundException;
}
