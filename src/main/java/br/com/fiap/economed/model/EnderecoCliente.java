package br.com.fiap.economed.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.economed.dto.CadastroEnderecoClienteDto;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "endereco_cliente")
@EntityListeners(AuditingEntityListener.class)
public class EnderecoCliente {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

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

    public EnderecoCliente(CadastroEnderecoClienteDto dto) {
        this.clienteId = dto.clienteId();
        this.rua = dto.rua();
        this.numero = dto.numero();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
    }

}