package com.yeseung.commutecheck.modules.commute.application.port.out;

import com.yeseung.commutecheck.modules.commute.domain.Commute;

public interface CommuteOutPort {

    Commute commute(String accountId);

}
