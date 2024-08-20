package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.commute.application.port.out.CommuteOutPort;
import com.yeseung.commutecheck.modules.commute.domain.Commute;
import com.yeseung.commutecheck.modules.commute.properties.CommuteProperties;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * 출퇴근 영속 어댑터
 *
 * @author seunggu.lee
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class CommutePersistenceAdapter implements CommuteOutPort {

    private final CommuteProperties commuteProperties;

    private final CommuteRepository commuteRepository;

    /**
     * 출근 처리
     *
     * @param accountId 계정 ID
     * @return 출퇴근 정보
     */
    @Override
    public Commute commute(String accountId) {
        commuteRepository.findByAccountIdAndCommuteDate(accountId, LocalDate.now()).ifPresent(commuteEntity -> {
            throw new IllegalArgumentException("이미 출근 처리 되었습니다.");
        });
        CommuteEntity commuteEntity = CommuteEntity.createNewCommute(accountId, commuteProperties.getLateTime());
        commuteRepository.save(commuteEntity);
        return commuteEntity.mapToDomain();
    }

}
