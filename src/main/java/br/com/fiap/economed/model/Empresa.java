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
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "cnpj", nullable = false, length = 20)
    private String cnpj;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

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
        this.cnpj = dto.cnpj();
        this.nome = dto.nome();
        this.tipo = dto.tipo();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.areaAtuacao = dto.areaAtuacao();
    }

}
