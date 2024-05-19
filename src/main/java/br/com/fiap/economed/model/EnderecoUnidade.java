package br.com.fiap.economed.model;

import br.com.fiap.economed.dto.enderecoUnidade.CadastroEnderecoUnidadeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_endereco_unidade")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoUnidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_unidade")
    @SequenceGenerator(name = "endereco_unidade", sequenceName = "cp1_endereco_unidade_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "rua", length = 100)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "cep", length = 20)
    private String cep;

    @OneToOne
    @JoinColumn(name = "unidade_id", unique = true)
    private Unidade unidade;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public EnderecoUnidade(CadastroEnderecoUnidadeDTO dto) {
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.cep = dto.cep();
    }
}