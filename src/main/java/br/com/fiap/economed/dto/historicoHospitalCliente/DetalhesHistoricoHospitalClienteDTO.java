package br.com.fiap.economed.dto.historicoHospitalCliente;

import br.com.fiap.economed.model.HistoricoHospitalCliente;

public record DetalhesHistoricoHospitalClienteDTO(
        Long id,
        String historicoMedico,
        String examesRealizados,
        String medicamentosPrescritos,
        String observacoes) {

    public DetalhesHistoricoHospitalClienteDTO(HistoricoHospitalCliente historicoHospitalCliente) {
        this(
                historicoHospitalCliente.getId(),
                historicoHospitalCliente.getHistoricoMedico(),
                historicoHospitalCliente.getExamesRealizados(),
                historicoHospitalCliente.getMedicamentosPrescritos(),
                historicoHospitalCliente.getObservacoes());
    }
}
