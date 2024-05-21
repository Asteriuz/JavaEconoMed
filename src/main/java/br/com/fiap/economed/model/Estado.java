package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.estado.AtualizacaoEstadoDTO;
import br.com.fiap.economed.dto.estado.CadastroEstadoDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    @SequenceGenerator(name = "estado", sequenceName = "cp1_estado_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    public Estado(CadastroEstadoDTO dto) {
        nome = dto.nome();
    }

    public void atualizar(AtualizacaoEstadoDTO dto) {
        nome = dto.nome();
    }
}