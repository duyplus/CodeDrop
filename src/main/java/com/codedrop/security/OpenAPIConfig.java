package com.codedrop.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("SRTeam");
        contact.setEmail("codedrop.contacts@gmail.com");

        Info info = new Info();
        info.title("Management System API - CodeDrop");
        info.version("1.0.0");
        info.description("This API exposes endpoints to manage system.\n\n© 2024 SRTeam.");
        info.contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
