package com.example.drugapprovalsystem.config;

import com.example.drugapprovalsystem.common.Common;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@Data
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( auth ->
                        auth
                                .requestMatchers("/v3/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/auth/login")
                                .permitAll()
                                .requestMatchers("/api/test/**")
                                .permitAll()
                                .requestMatchers("/public/**")
                                .permitAll()
                                .requestMatchers("/auth/register")
                                .hasAuthority(Common.ADMIN)
                                .requestMatchers("/admin/users/**")
                                .hasAuthority(Common.ADMIN)
                                .requestMatchers("/admin/users/email")
                                .hasAnyAuthority(Common.ADMIN,Common.SECRETARY)
                                .requestMatchers("/admin/profile-products/processing")
                                .hasAuthority(Common.ADMIN)
                                .requestMatchers("/admin/profile-products/submit")
                                .hasAuthority(Common.ADMIN)
                                .requestMatchers(HttpMethod.DELETE,"/admin/drugs")
                                .hasAuthority(Common.ADMIN)
                                .requestMatchers(HttpMethod.DELETE,"/admin/approval-products")
                                .hasAuthority(Common.ADMIN)
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
