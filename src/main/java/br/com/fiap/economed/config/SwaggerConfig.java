package br.com.fiap.economed.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Economed API", version = "v1", description = "API do projeto Economed"), tags = {
        @Tag(name = "Autenticação"),
        @Tag(name = "Clientes"),
        @Tag(name = "Histórico dos Hospitais"),
        @Tag(name = "Histórico de Saúde"),
        @Tag(name = "Convenios"),
        @Tag(name = "Empresas"),
        @Tag(name = "Unidades"),
        @Tag(name = "Estados"),
        @Tag(name = "Cidades"),
        @Tag(name = "Endereço do Cliente"),
        @Tag(name = "Endereço da Unidade"),
})
@SecurityScheme(name = "jwtBearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class SwaggerConfig {

}
