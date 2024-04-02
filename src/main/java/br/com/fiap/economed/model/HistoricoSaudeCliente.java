package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import br.com.fiap.economed.dto.CadastroHistoricoSaudeClienteDto;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "historico_saude_cliente")
@EntityListeners(AuditingEntityListener.class)
public class HistoricoSaudeCliente {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "fumo", length = 20)
    private String fumo;

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
        this.fumo = dto.fumo();
        this.doencaPrincipal = dto.doencaPrincipal();
        this.historicoFamiliar = dto.historicoFamiliar();
        this.alergias = dto.alergias();
        this.observacoes = dto.observacoes();
    }

}
