package com.yeseung.commutecheck.modules.commute.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties("commute")
public class CommuteProperties {

    /**
     * 지각 체크 시간
     */
    private String lateTime;

    /**
     * 지각 체크 횟수
     */
    private Integer lateCount;

}
