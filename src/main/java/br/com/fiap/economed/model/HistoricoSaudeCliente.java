package br.com.fiap.economed.model;

import br.com.fiap.economed.dto.historicoSaudeCliente.AtualizacaoHistoricoSaudeClienteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.historicoSaudeCliente.CadastroHistoricoSaudeClienteDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_historico_saude_cliente")
@EntityListeners(AuditingEntityListener.class)
public class HistoricoSaudeCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_saude_cliente")
    @SequenceGenerator(name = "historico_saude_cliente", sequenceName = "cp1_historico_saude_cliente_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "fuma")
    private Boolean fuma;

    //TODO: Trocar por um array de string "doenças"?
    @Column(name = "doenca_principal", length = 100)
    private String doencaPrincipal;

    @Column(name = "historico_familiar", length = 100)
    private String historicoFamiliar;

    @Column(name = "alergias", length = 100)
    private String alergias;

    @Column(name = "observacoes", length = 100)
    private String observacoes;

    public HistoricoSaudeCliente(CadastroHistoricoSaudeClienteDto dto) {
        this.clienteId = dto.clienteId();
        this.dataRegistro = dto.dataRegistro();
        this.fuma = dto.fuma();
        this.doencaPrincipal = dto.doencaPrincipal();
        this.historicoFamiliar = dto.historicoFamiliar();
        this.alergias = dto.alergias();
        this.observacoes = dto.observacoes();
    }

    public void atualizarDados(AtualizacaoHistoricoSaudeClienteDto dto) {
        if (dto.fuma() != null) {
            fuma = dto.fuma();
        }
        if (dto.doencaPrincipal() != null) {
            doencaPrincipal = dto.doencaPrincipal();
        }
        if (dto.historicoFamiliar() != null) {
            historicoFamiliar = dto.historicoFamiliar();
        }
        if (dto.alergias() != null) {
            alergias = dto.alergias();
        }
        if (dto.observacoes() != null) {
            observacoes = dto.observacoes();
        }
    }

}