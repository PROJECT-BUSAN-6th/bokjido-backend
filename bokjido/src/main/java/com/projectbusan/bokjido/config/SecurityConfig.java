/*
package com.projectbusan.bokjido.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    private final String[] allowedUrls = {"/", "/swagger-ui/**", "/v3/**"
            , "signup", "signin"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
//                .headers(headers -> headers.frameOptions().sameOrigin()) // H2 콘솔 사용을 위한 설정
                .authorizeHttpRequests(request ->
                        request.requestMatchers(allowedUrls).permitAll()  // requestMatchers의 인자로 전달된 url은 모두에게 허용
                                .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접속은 모두에게 허용
                                .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                // 세션을 사용하지 않으므로 STATELESS 설정
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
*/
