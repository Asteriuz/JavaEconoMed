package br.com.fiap.economed.model;

import br.com.fiap.economed.dto.cliente.AtualizacaoClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.cliente.CadastroClienteDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP1_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "rg", length = 20)
    private String rg;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", nullable = false, length = 20)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "estado_civil_id", referencedColumnName = "id")
    private EstadoCivil estadoCivil;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private EnderecoCliente endereco;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private HistoricoHospitalCliente historicoHospital;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "convenio_id", referencedColumnName = "id")
    private Convenio convenio;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private User user;

    public Cliente(CadastroClienteDTO dto) {
        rg = dto.rg();
        nome = dto.nome();
        telefone = dto.telefone();
        email = dto.email();
        dataNascimento = dto.dataNascimento();
        cpf = dto.cpf();

        endereco = new EnderecoCliente(dto.endereco());
        historicoHospital = new HistoricoHospitalCliente(dto.historicoHospital());
    }

    public void atualizar(AtualizacaoClienteDTO dto) {
        if (dto.rg() != null) {
            this.rg = dto.rg();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
            if (this.user != null) {
                this.user.setName(dto.nome());
            }
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.dataNascimento() != null) {
            this.dataNascimento = dto.dataNascimento();
        }
        if (dto.cpf() != null) {
            this.cpf = dto.cpf();
            if (this.user != null) {
                this.user.setLogin(dto.cpf());
            }
        }
    }
}