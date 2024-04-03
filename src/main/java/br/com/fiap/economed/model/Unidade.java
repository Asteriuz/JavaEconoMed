package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.unidade.AtualizacaoUnidadeDto;
import br.com.fiap.economed.dto.unidade.CadastroUnidadeDto;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_unidade")
@EntityListeners(AuditingEntityListener.class)
public class Unidade {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "empresa_id")
    private Long empresaId;

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

    @Column(name = "especialidades", length = 100)
    private String especialidades;

    public Unidade(CadastroUnidadeDto dto) {
        this.empresaId = dto.empresaId();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.tipo = dto.tipo();
        this.capacidade = dto.capacidade();
        this.especialidades = dto.especialidades();
    }

    public void atualizarDados(AtualizacaoUnidadeDto dto) {
        this.empresaId = dto.empresaId();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.tipo = dto.tipo();
        this.capacidade = dto.capacidade();
        this.especialidades = dto.especialidades();
    }

}
