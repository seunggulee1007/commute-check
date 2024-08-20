package com.yeseung.commutecheck.modules.commute.domain;

/**
 * 벌칙 도메인
 */
public record Penalty(
    // 계정 ID
    String accountId,
    // 벌칙 횟수
    Integer penaltyCount,
    // 지각 횟수
    Integer lateCount,
    // 차감횟수
    Integer deductionCount,
    // 총 지각 횟수
    Integer totalLateCount

) {

}
