package com.nkosi.programallocator.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private static final String SCHEME_NAME = "Bearer Scheme";

    private static final String SCHEME = "Bearer";

    private static final String AUTHORIZATION_FLOW = "Authorization: Bearer ";

    private static final String AUTHORIZATION_DESCRIPTION = "Bearer token based authorisation";

    private static final String CONTACT_EMAIL = "engineer@kwazwide.com";

    private static final String AUTHOR_ORG = "kwaZwide";

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openApi = new OpenAPI().info(getInfo());
        addSecurity(openApi);
        return openApi;
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
        return new License()
                .name("Proprietary")
                .url("https//www.kwazwide.com/license");
    }

    private void addSecurity(OpenAPI openApi) {
        Components components = createComponents();
        SecurityRequirement securityItem = new SecurityRequirement().addList(SCHEME_NAME);
        openApi.components(components)
                .addSecurityItem(securityItem);
    }

    private Components createComponents() {
        Components components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());
        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .in(SecurityScheme.In.HEADER)
                .bearerFormat(AUTHORIZATION_FLOW)
                .description(AUTHORIZATION_DESCRIPTION)
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

}
