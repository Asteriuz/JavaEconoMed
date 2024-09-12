package br.com.fiap.economed.dto.autenticacao;

import br.com.fiap.economed.model.enums.UserRole;

public record RegistroDTO(String login, String password, UserRole role, String name) {
}
