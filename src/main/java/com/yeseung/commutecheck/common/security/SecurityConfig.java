package com.yeseung.commutecheck.common.security;

import com.yeseung.commutecheck.common.properties.AppProperties;
import com.yeseung.commutecheck.common.properties.JwtProperties;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.EntryPointHandler;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.Jwt;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.JwtAccessDeniedHandler;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Jwt jwt;

    private final JwtProperties jwtProperties;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final EntryPointHandler unAuthorizedHandler;

    private final AppProperties appProperties;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtProperties.getHeader(), jwt);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            .exceptionHandling(handle -> handle.accessDeniedHandler(jwtAccessDeniedHandler).authenticationEntryPoint(unAuthorizedHandler)).headers(
                config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)).sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)).authorizeHttpRequests(request -> request.requestMatchers("/swagger-ui/**",
                                                                                                           "/v3/api-docs/**",
                                                                                                           "/swagger-resources/**",
                                                                                                           "/api/account/auth/**",
                                                                                                           "/actuator/**").permitAll()
                .anyRequest().authenticated()).addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        List<String> allowDomains = appProperties.getAllowDomains();
        config.setAllowedOrigins(allowDomains);
        config.setAllowedMethods(List.of("GET", "PUT", "DELETE", "POST", "PATCH", "OPTIONS", "HEAD"));
        config.setExposedHeaders(List.of("Access-Control-Allow-Headers",
                                         "ACCESS_TOKEN",
                                         "Access-Control-Allow-Origin",
                                         "strict-origin-when-cross-origin"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
