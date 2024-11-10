package com.devvictor.ecommerce_api.shared.infra.security;


import com.devvictor.ecommerce_api.user.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()

                        // products
                        .requestMatchers(HttpMethod.GET, "/products").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.GET, "/products/{id}").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAuthority(Role.ADMIN.name())

                        // carts
                        .requestMatchers(HttpMethod.GET, "/carts/my-cart").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.DELETE, "/carts/my-cart").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.POST, "/carts/my-cart/products/{productId}").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.DELETE, "/carts/my-cart/products/{productId}").hasAuthority(Role.CLIENT.name())

                        // orders
                        .requestMatchers(HttpMethod.GET, "/orders").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/orders/{orderId}").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.GET, "/orders/my-orders").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.POST, "/orders/my-orders").hasAuthority(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.DELETE, "/orders/my-orders/{orderId}").hasAuthority(Role.CLIENT.name())

                        // payments
                        .requestMatchers(HttpMethod.POST, "/payments/credit/orders/{orderId}").hasAnyAuthority(Role.CLIENT.name())

                        // any
                        .anyRequest().denyAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
