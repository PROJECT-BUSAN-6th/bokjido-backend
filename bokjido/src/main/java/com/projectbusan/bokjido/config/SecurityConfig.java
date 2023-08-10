package com.projectbusan.bokjido.config;

import com.projectbusan.bokjido.auth.JwtAccessDeniedHandler;
import com.projectbusan.bokjido.auth.JwtAuthenticationEntryPoint;
import com.projectbusan.bokjido.auth.JwtAuthenticationFilter;
import com.projectbusan.bokjido.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.projectbusan.bokjido.config", "com.projectbusan.bokjido.auth"})
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    private final String[] allowedUrls = {"/", "/swagger-ui/**", "/v3/**"
            , "/api-docs/**", "/api/auth/**", };

    private final String[] allowedForUser = {"/api/service/loadall"};

    private final String[] allowedForAdmin = {"/api/service/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(options -> options.sameOrigin())) // H2 콘솔 사용을 위한 설정
                .exceptionHandling(request -> request
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 에러 핸들링
                        .accessDeniedHandler(jwtAccessDeniedHandler)) // 403 에러 핸들링
                .authorizeHttpRequests(request ->
                                request
                                        .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접속은 모두에게 허용
                                        .requestMatchers(Stream
                                                .of(allowedUrls)
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(AntPathRequestMatcher[]::new)).permitAll()  // requestMatchers의 인자로 전달된 url은 모두에게 허용
                                        .requestMatchers(Stream
                                                .of(allowedForUser)
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(AntPathRequestMatcher[]::new)).hasAnyRole("USER", "ADMIN")
                                        .requestMatchers(Stream
                                                .of(allowedForAdmin)
                                                .map(AntPathRequestMatcher::antMatcher)
                                                .toArray(AntPathRequestMatcher[]::new)).hasRole("ADMIN")

                                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                // 세션을 사용하지 않으므로 STATELESS 설정
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
