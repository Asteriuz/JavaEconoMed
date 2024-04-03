package br.com.fiap.economed.model;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.cliente.CadastroClienteDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "rg", nullable = false, length = 20)
    private String rg;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 20)
    private String cpf;

    //TODO: Transformar em enum
    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;

    @Column(name = "convenio_id")
    private Long convenioId;
    
    @Transient // Testando Transient por curiosidade, apagar depois
    private String token;

    public Cliente(CadastroClienteDto dto) {
        this.rg = dto.rg();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
        this.cpf = dto.cpf();
        this.estadoCivil = dto.estadoCivil();
        this.convenioId = dto.convenioId();
    }

    public void atualizarDados(AtualizacaoClienteDto dto) {
        this.rg = dto.rg();
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
        this.cpf = dto.cpf();
        this.estadoCivil = dto.estadoCivil();
        this.convenioId = dto.convenioId();
    }

}