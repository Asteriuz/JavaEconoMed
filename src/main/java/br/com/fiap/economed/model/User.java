package br.com.fiap.economed.model;

import br.com.fiap.economed.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CP1_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=50)
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Empresa empresa;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Cliente cliente;

    public User(String login, String password, UserRole role, String name) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.name = cliente.getNome();
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        this.name = empresa.getNome();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_EMPRESA"), new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }

        if(this.role == UserRole.EMPRESA) {
            return List.of(new SimpleGrantedAuthority("ROLE_EMPRESA"));
        }

        if (this.role == UserRole.CLIENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }

        throw new IllegalArgumentException("Role not found");
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
