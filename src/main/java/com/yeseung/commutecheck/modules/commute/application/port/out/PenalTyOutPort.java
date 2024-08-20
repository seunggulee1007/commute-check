package com.yeseung.commutecheck.modules.commute.application.port.out;

import com.yeseung.commutecheck.modules.commute.domain.Penalty;

public interface PenalTyOutPort {

    Penalty updatePenalty(String accountId);

}
