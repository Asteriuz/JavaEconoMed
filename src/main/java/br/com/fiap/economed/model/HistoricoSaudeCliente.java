package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.historicoSaudeCliente.CadastroHistoricoSaudeClienteDTO;

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

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "fuma")
    private Boolean fuma;

    @Column(name = "observacoes", length = 100)
    private String observacoes;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    public HistoricoSaudeCliente(CadastroHistoricoSaudeClienteDTO dto) {
        this.dataRegistro = dto.dataRegistro();
        this.fuma = dto.fuma();
        this.observacoes = dto.observacoes();
    }
}