package com.yeseung.commutecheck.modules.crawling.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties("crawler")
public class CrawlerProperties {

    private CGV cgv;

}
