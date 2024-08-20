package com.yeseung.commutecheck.modules.commute.application.service;

import com.yeseung.commutecheck.common.annotations.ServiceAdapter;
import com.yeseung.commutecheck.modules.commute.application.port.in.PenaltyUseCase;
import com.yeseung.commutecheck.modules.commute.application.port.out.PenalTyOutPort;
import com.yeseung.commutecheck.modules.commute.domain.Penalty;
import lombok.RequiredArgsConstructor;

@ServiceAdapter
@RequiredArgsConstructor
public class PenaltyService implements PenaltyUseCase {

    private final PenalTyOutPort penaltyOutPort;

    @Override
    public Penalty updatePenalty(String accountId) {
        return penaltyOutPort.updatePenalty(accountId);
    }

}
