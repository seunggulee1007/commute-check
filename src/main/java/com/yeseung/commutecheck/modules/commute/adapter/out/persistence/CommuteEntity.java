package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import com.yeseung.commutecheck.common.entity.UpdatedEntity;
import com.yeseung.commutecheck.modules.commute.domain.Commute;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Entity
@Table(name = "commute")
public class CommuteEntity extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID commuteId;

    /**
     * 계정 ID
     */
    private String accountId;
    /**
     * 출근 일자
     */
    private LocalDate commuteDate;
    /**
     * 출근 시간
     */
    private LocalDateTime inTime;
    /**
     * 퇴근 시간
     */
    private LocalDateTime outTime;
    /**
     * 지각 여부
     */
    private boolean late;

    public static CommuteEntity createNewCommute(String accountId, String lateTime) {
        CommuteEntity commuteEntity = new CommuteEntity();
        commuteEntity.commuteId = UUID.randomUUID();
        commuteEntity.accountId = accountId;
        commuteEntity.commuteDate = LocalDate.now();
        commuteEntity.inTime = LocalDateTime.now();
        commuteEntity.late = commuteEntity.isLate(lateTime);
        return commuteEntity;
    }

    private boolean isLate(String lateTime) {
        LocalDate currentDate = LocalDate.now();
        String currentDateString = currentDate.toString().concat(" ").concat(lateTime).concat(":59");
        LocalDateTime baseTime = LocalDateTime.parse(currentDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return LocalDateTime.now().isAfter(baseTime);
    }

    public Commute mapToDomain() {
        return Commute.builder()
            .commuteId(commuteId.toString())
            .accountId(accountId)
            .commuteDate(commuteDate)
            .inTime(inTime)
            .outTime(outTime)
            .late(late)
            .build();
    }

}
