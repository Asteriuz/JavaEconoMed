package br.com.fiap.economed.controller;

import br.com.fiap.economed.config.TokenService;
import br.com.fiap.economed.dto.autenticacao.AutenticacaoDTO;
import br.com.fiap.economed.dto.autenticacao.LoginResponseDTO;
import br.com.fiap.economed.dto.autenticacao.RegistroDTO;
import br.com.fiap.economed.model.Cliente;
import br.com.fiap.economed.model.Empresa;
import br.com.fiap.economed.model.User;
import br.com.fiap.economed.model.enums.UserRole;
import br.com.fiap.economed.repository.ClienteRepository;
import br.com.fiap.economed.repository.EmpresaRepository;
import br.com.fiap.economed.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data) {
        log.info("Autenticando usuário: {}", data.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        log.info("Usuário autenticado com sucesso: {}", data.login());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistroDTO data) {
        log.info("Registrando usuário: {}", data.login());

        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.name());

        this.userRepository.save(newUser);

        if (data.role() == UserRole.CLIENTE) {
            Cliente newCliente = new Cliente();
            newCliente.setId(newUser.getId());
            newCliente.setUser(newUser);
            newCliente.setCpf(data.login());
            newCliente.setNome(data.name());
            clienteRepository.save(newCliente);
            newUser.setCliente(newCliente);
        }

        if (data.role() == UserRole.EMPRESA) {
            Empresa newEmpresa = new Empresa();
            newEmpresa.setId(newUser.getId());
            newEmpresa.setUser(newUser);
            newEmpresa.setCnpj(data.login());
            newEmpresa.setNome(data.name());
            empresaRepository.save(newEmpresa);
            newUser.setEmpresa(newEmpresa);
        }

        this.userRepository.save(newUser);

        log.info("Usuário registrado com sucesso: {}", data.login());
        return ResponseEntity.ok().build();
    }

}
