package com.nkosi.programallocator.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private static final String CONTACT_EMAIL = "n02124360x@students.nust.ac.zw";

    private static final String AUTHOR_ORG = "Iknow";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Program Allocator Service")
                .contact(getContact())
                .description("Program Allocation service")
                .version("1.0.1")
                .license(getLicense());
    }

    private Contact getContact() {
        return new Contact()
                .email(CONTACT_EMAIL)
                .name(AUTHOR_ORG);
    }

    private License getLicense() {
        return new License();
    }
}

