package com.yeseung.commutecheck.modules.commute.adapter.in.web;

import com.yeseung.commutecheck.modules.commute.domain.Commute;
import com.yeseung.commutecheck.modules.commute.domain.Penalty;
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

    // 벌칙 횟수
    private Integer penaltyCount;
    // 지각 횟수
    private Integer lateCount;
    // 차감횟수
    private Integer deductionCount;
    // 총 지각 횟수
    private Integer totalLateCount;

    public static CommuteResponse from(Commute commute) {
        CommuteResponse commuteResponse = new CommuteResponse();
        commuteResponse.commuteId = commute.commuteId();
        commuteResponse.accountId = commute.accountId();
        commuteResponse.commuteDate = commute.commuteDate();
        commuteResponse.inTime = commute.inTime();
        commuteResponse.outTime = commute.outTime();
        commuteResponse.late = commute.late();
        
        return commuteResponse;
    }

    public void updatePenalty(Penalty penalty) {
        this.penaltyCount = penalty.penaltyCount();
        this.lateCount = penalty.lateCount();
        this.deductionCount = penalty.deductionCount();
        this.totalLateCount = penalty.totalLateCount();
    }

}
