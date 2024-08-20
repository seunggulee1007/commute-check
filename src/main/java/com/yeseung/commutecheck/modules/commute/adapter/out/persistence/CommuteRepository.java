package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<CommuteEntity, Long> {

    /**
     * 계정 ID와 출근 일자로 출퇴근 정보 조회
     *
     * @param accountId 계정 ID
     * @param now       출근 일자
     * @return 출퇴근 정보
     */
    Optional<CommuteEntity> findByAccountIdAndCommuteDate(String accountId, LocalDate now);

}
