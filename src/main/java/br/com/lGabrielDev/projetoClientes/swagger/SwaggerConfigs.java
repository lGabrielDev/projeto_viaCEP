package br.com.lGabrielDev.projetoClientes.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfigs {
    
    @Bean
    public OpenAPI setup(){
        return new OpenAPI()
            .info(
                new Info().title("Cadastro de Clientes").description("Validando CEP através da API externa 'ViaCEP' ")
                    .license(
                        new License().name("MIT").url("https://opensource.org/license/mit")
                    )
                    .contact(
                        new Contact().email("thegabrielfreitasbf@yahoo.com.br").name("lGabrielDev")
                    )
                    .version("1.0")
            )
            .servers(
                List.of(
                    new Server().url("http://localhost:8080")
                    .description("")
                )
            );
    }
}