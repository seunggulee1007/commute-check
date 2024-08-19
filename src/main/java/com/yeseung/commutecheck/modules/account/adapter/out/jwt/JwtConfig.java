package com.yeseung.commutecheck.modules.account.adapter.out.jwt;

import com.yeseung.commutecheck.common.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JwtConfig {

    @Bean
    public Jwt jwt(JwtProperties jwtProperties) {
        log.error("jwtProperties: {}", jwtProperties);
        return new Jwt(jwtProperties.getIssuer(), jwtProperties.getClientSecret());
    }

}
