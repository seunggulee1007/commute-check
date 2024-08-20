package com.yeseung.commutecheck.modules.commute.application.port.in;

import com.yeseung.commutecheck.modules.commute.domain.Penalty;

public interface PenaltyUseCase {

    Penalty updatePenalty(String accountId);

}
