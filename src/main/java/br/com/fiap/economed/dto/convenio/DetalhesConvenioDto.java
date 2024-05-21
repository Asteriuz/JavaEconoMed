package br.com.fiap.economed.dto.convenio;

import br.com.fiap.economed.model.Convenio;
import br.com.fiap.economed.dto.empresa.DetalhesEmpresaDTO;

import java.time.LocalDate;

public record DetalhesConvenioDTO(
        Long id,
        DetalhesEmpresaDTO empresa,
        String nome,
        Double valor,
        String tipoServico,
        String cobertura,
        String contato,
        LocalDate validade) {

    public DetalhesConvenioDTO(Convenio convenio) {
        this(
                convenio.getId(),
                new DetalhesEmpresaDTO(convenio.getEmpresa()),
                convenio.getNome(),
                convenio.getValor(),
                convenio.getTipoServico(),
                convenio.getCobertura(),
                convenio.getContato(),
                convenio.getValidade());
    }
}
