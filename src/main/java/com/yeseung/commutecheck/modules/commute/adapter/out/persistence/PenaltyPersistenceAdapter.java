package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.commute.application.port.out.PenalTyOutPort;
import com.yeseung.commutecheck.modules.commute.domain.Penalty;
import com.yeseung.commutecheck.modules.commute.properties.CommuteProperties;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PenaltyPersistenceAdapter implements PenalTyOutPort {

    private final PenaltyRepository penaltyRepository;
    private final CommuteProperties commuteProperties;

    @Override
    public Penalty updatePenalty(String accountId) {
        PenaltyEntity penaltyEntity = penaltyRepository.findByAccountId(accountId).orElseGet(() -> PenaltyEntity.of(accountId));
        penaltyEntity.updatePenalty(commuteProperties.getLateCount());
        penaltyRepository.save(penaltyEntity);
        return penaltyEntity.mapToDomain();
    }

}
