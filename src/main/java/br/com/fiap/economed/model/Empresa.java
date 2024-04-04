package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDto;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_empresa")
@EntityListeners(AuditingEntityListener.class)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa")
    @SequenceGenerator(name = "empresa", sequenceName = "cp1_empresa_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "cnpj", nullable = false, length = 20)
    private String cnpj;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    // TODO: Quais tipos de empresas podem ser cadastradas? Vamos criar um enum pra
    // isso?
    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    // TODO: Quais áreas de atuação podem ser cadastradas? Vamos criar um enum pra
    // isso?
    @Column(name = "area_atuacao", nullable = false, length = 100)
    private String areaAtuacao;

    public Empresa(CadastroEmpresaDto dto) {
        this.cnpj = dto.cnpj();
        this.nome = dto.nome();
        this.tipo = dto.tipo();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.areaAtuacao = dto.areaAtuacao();
    }

    public void atualizarDados(AtualizacaoEmpresaDto dto) {
        if (dto.cnpj() != null) {
            this.cnpj = dto.cnpj();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.tipo() != null) {
            this.tipo = dto.tipo();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.areaAtuacao() != null) {
            this.areaAtuacao = dto.areaAtuacao();
        }
    }

}
