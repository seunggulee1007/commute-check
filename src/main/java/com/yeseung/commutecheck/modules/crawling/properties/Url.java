package com.yeseung.commutecheck.modules.crawling.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties("crawler.cgv.url")
public class Url {

    private String defaultUrl;
    private String image;
    private String movie;

}
