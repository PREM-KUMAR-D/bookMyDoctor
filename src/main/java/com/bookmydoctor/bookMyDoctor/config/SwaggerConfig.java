package com.bookmydoctor.bookMyDoctor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookMyDoctorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book My Doctor API")
                        .description("API documentation for appointment booking, doctors, users, and leaves")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Prem Kumar")
                                .email("prem@example.com")
                                .url("https://github.com/your-github")));
    }
}
