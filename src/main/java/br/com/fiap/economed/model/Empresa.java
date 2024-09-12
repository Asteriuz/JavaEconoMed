package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.empresa.CadastroEmpresaDTO;
import br.com.fiap.economed.dto.empresa.AtualizacaoEmpresaDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_empresa")
@EntityListeners(AuditingEntityListener.class)
public class Empresa {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cnpj", nullable = false, length = 20)
    private String cnpj;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL)
    private User user;

    public Empresa(CadastroEmpresaDTO dto) {
        this.cnpj = dto.cnpj();
        this.nome = dto.nome();
        this.tipo = dto.tipo();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

    public void atualizar(AtualizacaoEmpresaDTO dto) {
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
    }
}
