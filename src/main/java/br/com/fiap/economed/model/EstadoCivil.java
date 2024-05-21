package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_estado_civil")
@EntityListeners(AuditingEntityListener.class)
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_civil")
    @SequenceGenerator(name = "estado_civil", sequenceName = "cp1_estado_civil_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

}