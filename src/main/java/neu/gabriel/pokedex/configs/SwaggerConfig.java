package neu.gabriel.pokedex.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokédex API")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de Pokémon - Sistema completo de cadastro, consulta, atualização e exclusão de Pokémon com seus tipos, habilidades e características.")
                        .contact(new Contact()
                                .name("Gabriel Neu")
                                .email("gabrielbarbosaneu@gmail.com.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}