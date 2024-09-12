package br.com.fiap.economed.model.enums;

public enum UserRole {
    ADMIN("admin"),
    EMPRESA("empresa"),
    CLIENTE("cliente");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
