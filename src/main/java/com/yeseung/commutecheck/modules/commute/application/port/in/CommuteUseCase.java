package com.yeseung.commutecheck.modules.commute.application.port.in;

import com.yeseung.commutecheck.modules.commute.domain.Commute;

public interface CommuteUseCase {

    Commute commute(String accountId);

}
