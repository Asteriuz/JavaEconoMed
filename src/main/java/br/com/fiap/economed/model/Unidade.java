package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDTO;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_unidade")
@EntityListeners(AuditingEntityListener.class)
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade")
    @SequenceGenerator(name = "unidade", sequenceName = "cp1_unidade_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "capacidade")
    private Integer capacidade;

    public Unidade(CadastroUnidadeDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.tipo = dto.tipo();
        this.capacidade = dto.capacidade();
    }

    public void atualizarDados(AtualizacaoUnidadeDTO dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.tipo() != null) {
            this.tipo = dto.tipo();
        }
        if (dto.capacidade() != null) {
            this.capacidade = dto.capacidade();
        }
    }

}
