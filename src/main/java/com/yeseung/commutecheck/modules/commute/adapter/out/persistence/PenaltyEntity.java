package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import com.yeseung.commutecheck.common.entity.UpdatedEntity;
import com.yeseung.commutecheck.modules.commute.domain.Penalty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "penalty")
public class PenaltyEntity extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID penaltyId;

    /**
     * 계정 ID
     */
    private String accountId;
    /**
     * 벌칙 횟수
     */
    private Integer penaltyCount;
    /**
     * 지각 횟수
     */
    private Integer lateCount;
    // 차감횟수
    private Integer deductionCount;
    // 총 지각 횟수
    private Integer totalLateCount;

    public static PenaltyEntity of(String accountId) {
        PenaltyEntity entity = new PenaltyEntity();
        entity.penaltyId = UUID.randomUUID();
        entity.accountId = accountId;
        entity.penaltyCount = 0;
        entity.lateCount = 0;
        entity.deductionCount = 0;
        entity.totalLateCount = 0;
        return entity;
    }

    public Penalty mapToDomain() {
        return Penalty.builder()
            .penaltyId(penaltyId.toString())
            .accountId(accountId)
            .penaltyCount(penaltyCount)
            .lateCount(lateCount)
            .deductionCount(deductionCount)
            .totalLateCount(totalLateCount)
            .build();
    }

    public void updatePenalty(Integer lateCheckCount) {
        this.lateCount++;
        if (this.lateCount.equals(lateCheckCount)) {
            this.penaltyCount++;
            this.lateCount = 0;
        }
        this.totalLateCount++;
    }

}
