package br.com.fiap.economed.dto;

import java.time.LocalDate;

public record CadastroHistoricoHospitalClienteDto(
        Long clienteId,
        LocalDate dataRegistro,
        String historicoMedico,
        String examesRealizados,
        String medicamentosPrescritos,
        String observacoes) {
}