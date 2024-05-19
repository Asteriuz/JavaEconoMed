package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.historicoHospitalCliente.CadastroHistoricoHospitalClienteDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_historico_hospital_cliente")
@EntityListeners(AuditingEntityListener.class)
public class HistoricoHospitalCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_hospital_cliente")
    @SequenceGenerator(name = "historico_hospital_cliente", sequenceName = "cp1_historico_hospital_cliente_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    public HistoricoHospitalCliente(CadastroHistoricoHospitalClienteDTO dto) {
        dataRegistro = dto.dataRegistro();
        historicoMedico = dto.historicoMedico();
        examesRealizados = dto.examesRealizados();
        medicamentosPrescritos = dto.medicamentosPrescritos();
        observacoes = dto.observacoes();
    }

}