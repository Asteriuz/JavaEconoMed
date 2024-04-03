package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.convenio.AtualizacaoConvenioDto;
import br.com.fiap.economed.dto.convenio.CadastroConvenioDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_convenio")
@EntityListeners(AuditingEntityListener.class)
public class Convenio {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "empresa_id")
    private Long empresaId;

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

    public Convenio(CadastroConvenioDto dto) {
        this.empresaId = dto.empresaId();
        this.nome = dto.nome();
        this.valor = dto.valor();
        this.tipoServico = dto.tipoServico();
        this.cobertura = dto.cobertura();
        this.contato = dto.contato();
        this.validade = dto.validade();
    }

    public void atualizarDados(AtualizacaoConvenioDto dto) {
        this.nome = dto.nome();
        this.valor = dto.valor();
        this.tipoServico = dto.tipoServico();
        this.cobertura = dto.cobertura();
        this.contato = dto.contato();
        this.validade = dto.validade();
    }

}
