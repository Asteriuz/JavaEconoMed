package br.com.fiap.economed.service;

import br.com.fiap.economed.dto.historicoHospitalCliente.AtualizacaoHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDto;
import br.com.fiap.economed.dto.historicoHospitalCliente.DetalhesHistoricoHospitalClienteDto;
import br.com.fiap.economed.model.HistoricoHospitalCliente;
import br.com.fiap.economed.repository.HistoricoHospitalClienteRepository;
import br.com.fiap.economed.service.interfaces.IHistoricoHospitalClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HistoricoHospitalClienteService implements IHistoricoHospitalClienteService {

    private final HistoricoHospitalClienteRepository
            historicoHospitalClienteRepository;

    public HistoricoHospitalClienteService(HistoricoHospitalClienteRepository historicoHospitalClienteRepository) {
        this.historicoHospitalClienteRepository = historicoHospitalClienteRepository;
    }

    @Override
    public DetalhesHistoricoHospitalClienteDto buscarHistoricoHospital(Long clienteId) throws EntityNotFoundException {
        var historicoHospitalar = historicoHospitalClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        return new DetalhesHistoricoHospitalClienteDto(historicoHospitalar);
    }

    @Override
    public HistoricoHospitalCliente cadastrarHistoricoHospital(CadastroHistoricoHospitalClienteDto historicoHospitalClienteDto) {
        var historicoHospitalCliente = new HistoricoHospitalCliente(historicoHospitalClienteDto);
        return historicoHospitalClienteRepository.save(historicoHospitalCliente);
    }

    @Override
    public HistoricoHospitalCliente atualizarHistoricoHospital(Long clienteId, AtualizacaoHistoricoHospitalClienteDto atualizacaoHistoricoHospitalClienteDto) throws EntityNotFoundException {
        var historicoHospitalar = historicoHospitalClienteRepository.findByClienteId(clienteId).
                orElseThrow(EntityNotFoundException::new);
        historicoHospitalar.atualizarDados(atualizacaoHistoricoHospitalClienteDto);
        return historicoHospitalClienteRepository.save(historicoHospitalar);
    }
}
