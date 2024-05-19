package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.cidade.AtualizacaoCidadeDTO;
import br.com.fiap.economed.dto.cidade.CadastroCidadeDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_cidade")
@EntityListeners(AuditingEntityListener.class)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade")
    @SequenceGenerator(name = "cidade", sequenceName = "cp1_cidade_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    public Cidade(CadastroCidadeDTO dto) {
        nome = dto.nome();
    }

    public void atualizar(AtualizacaoCidadeDTO dto) {
        nome = dto.nome();
    }
}