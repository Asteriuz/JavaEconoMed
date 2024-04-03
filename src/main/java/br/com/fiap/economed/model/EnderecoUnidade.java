package br.com.fiap.economed.model;

import br.com.fiap.economed.dto.unidadeEndereco.AtualizacaoEnderecoUnidadeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.unidadeEndereco.CadastroEnderecoUnidadeDto;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_endereco_unidade")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoUnidade {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "unidade_id")
    private Long unidadeId;

    @Column(name = "rua", length = 100)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "estado", length = 100)
    private String estado;

    @Column(name = "cep", length = 20)
    private String cep;

    public EnderecoUnidade(CadastroEnderecoUnidadeDto dto) {
        this.unidadeId = dto.unidadeId();
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

    public void atualizarDados(AtualizacaoEnderecoUnidadeDto dto) {
        if (dto.rua() != null)
            rua = dto.rua();
        if (dto.numero() != null)
            numero = dto.numero();
        if (dto.cidade() != null)
            cidade = dto.cidade();
        if (dto.estado() != null)
            estado = dto.estado();
        if (dto.cep() != null)
            cep = dto.cep();
    }
}