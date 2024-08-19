package com.yeseung.commutecheck.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("jwt.token")
@ToString
public class JwtProperties {

    private String header;

    private String issuer;

    private String clientSecret;

    private String tokenValidityInMilliseconds;

}