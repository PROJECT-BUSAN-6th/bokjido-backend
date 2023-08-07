package com.projectbusan.bokjido.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "복지도 API 명세서",
        description = "복지서비스 API 명세서",
        version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi bokjidoApi() {
        String[] paths = {"/v1/**"};

        return GroupedOpenApi.builder()
                .group("복지서비스 API v1")
                .pathsToMatch(paths)
                .build();
    }
}
