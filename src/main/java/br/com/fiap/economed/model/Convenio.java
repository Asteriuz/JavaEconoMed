package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDTO;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_convenio")
@EntityListeners(AuditingEntityListener.class)
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "convenio")
    @SequenceGenerator(name = "convenio", sequenceName = "cp1_convenio_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "tipo_servico", length = 100)
    private String tipoServico;

    @Column(name = "cobertura", length = 100)
    private String cobertura;

    @Column(name = "contato", length = 100)
    private String contato;

    @Column(name = "validade")
    private LocalDate validade;

    public Convenio(CadastroConvenioDTO dto) {
        this.nome = dto.nome();
        this.valor = dto.valor();
        this.tipoServico = dto.tipoServico();
        this.cobertura = dto.cobertura();
        this.contato = dto.contato();
        this.validade = dto.validade();
    }

    public void atualizarDados(AtualizacaoConvenioDTO dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.valor() != null) {
            this.valor = dto.valor();
        }
        if (dto.tipoServico() != null) {
            this.tipoServico = dto.tipoServico();
        }
        if (dto.cobertura() != null) {
            this.cobertura = dto.cobertura();
        }
        if (dto.contato() != null) {
            this.contato = dto.contato();
        }
        if (dto.validade() != null) {
            this.validade = dto.validade();
        }
    }

}
