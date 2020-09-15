package br.cadastro.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi (@Value("Crud Simples") String descricao , @Value("0.5") String version ) {
		return new OpenAPI()
				.info(new Info()
						.title("Api para Gerência de Recursos Humanos")
						.version(version)
						.description(descricao)
						.contact(new Contact().name("João Carlos Belmiro Gonçalves").email("joao_belmiro@hotmail.com").url("https://github.com/joao-belmiro"))
				        .termsOfService("http://swagger.io/terms/")
				        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
