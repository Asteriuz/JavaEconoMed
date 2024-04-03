package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_historico_hospital_cliente")
@EntityListeners(AuditingEntityListener.class)
public class HistoricoHospitalCliente {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "historico_medico", length = 100)
    private String historicoMedico;

    @Column(name = "exames_realizados", length = 100)
    private String examesRealizados;

    @Column(name = "medicamentos_prescritos", length = 100)
    private String medicamentosPrescritos;

    @Column(name = "observacoes", length = 100)
    private String observacoes;

    public HistoricoHospitalCliente(CadastroHistoricoHospitalClienteDto dto) {
        this.clienteId = dto.clienteId();
        this.dataRegistro = dto.dataRegistro();
        this.historicoMedico = dto.historicoMedico();
        this.examesRealizados = dto.examesRealizados();
        this.medicamentosPrescritos = dto.medicamentosPrescritos();
        this.observacoes = dto.observacoes();
    }

}