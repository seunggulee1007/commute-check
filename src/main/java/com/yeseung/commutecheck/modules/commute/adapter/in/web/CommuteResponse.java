package com.yeseung.commutecheck.modules.commute.adapter.in.web;

import com.yeseung.commutecheck.modules.commute.domain.Commute;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class CommuteResponse {

    // 출퇴근 ID
    private String commuteId;
    // 계정 ID
    private String accountId;
    // 출근 일자
    private LocalDate commuteDate;
    // 출근 시간
    private LocalDateTime inTime;
    // 퇴근 시간
    private LocalDateTime outTime;
    // 지각 여부
    private boolean late;

    public static CommuteResponse from(Commute commute) {
        return new CommuteResponse();
    }

}
