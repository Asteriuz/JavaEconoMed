package br.com.fiap.economed.service.interfaces;

import br.com.fiap.economed.dto.historicoHospitalCliente.AtualizacaoHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDto;
import br.com.fiap.economed.model.HistoricoHospitalCliente;
import jakarta.persistence.EntityNotFoundException;

public interface IHistoricoHospitalClienteService {
    DetalhesHistoricoHospitalClienteDto buscarHistoricoHospital(Long clienteId) throws EntityNotFoundException;
    HistoricoHospitalCliente cadastrarHistoricoHospital(CadastroHistoricoHospitalClienteDto historicoHospitalClienteDto);
    HistoricoHospitalCliente atualizarHistoricoHospital(Long clienteId, AtualizacaoHistoricoHospitalClienteDto atualizacaoHistoricoHospitalClienteDto) throws EntityNotFoundException;
}
