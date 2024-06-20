package com.girichev.security.config;

import com.girichev.security.jwt.filter.JwtRequestFilter;
import com.girichev.security.service.ClientDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    ClientDetailsService clientDetailsService;
    BCryptPasswordEncoder passwordEncoder;
    JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors(this::configureCors)
                .authorizeRequests()
                .antMatchers("/client/swagger-ui/**").permitAll()
                .antMatchers("/client/v2/api-docs/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .antMatchers("/api/v1/**").hasAnyRole("CLIENT", "ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return httpSecurity.build();
    }

    private void configureCors(CorsConfigurer<HttpSecurity> cors) {
        cors
                .configurationSource(request -> {
                    if (request.getRequestURI().contains("/client/api/v1/")) { // Применяем CORS только к /api/v1/ TODO: сваггер не доступен попробуй "/client/**"
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("*")); // Разрешаем доступ с любых источников
                        config.setAllowedMethods(List.of("*")); // Разрешаем использование всех HTTP методов
                        config.setAllowedHeaders(List.of("*")); // Разрешаем передачу всех заголовков
                        return config;
                    } else {
                        return null; // Возвращаем null для остальных запросов (без изменений CORS)
                    }
                });
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(clientDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
