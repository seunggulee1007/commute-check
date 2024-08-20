package com.yeseung.commutecheck.modules.commute.domain;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record Commute(
    // 출퇴근 ID
    String commuteId,
    // 계정 ID
    String accountId,
    // 출근 일자
    LocalDate commuteDate,
    // 출근 시간
    LocalDateTime inTime,
    // 퇴근 시간
    LocalDateTime outTime,
    // 지각 여부
    boolean late
) {

}
