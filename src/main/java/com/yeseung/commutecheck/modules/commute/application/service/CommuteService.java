package com.yeseung.commutecheck.modules.commute.application.service;

import com.yeseung.commutecheck.common.annotations.ServiceAdapter;
import com.yeseung.commutecheck.modules.commute.application.port.in.CommuteUseCase;
import com.yeseung.commutecheck.modules.commute.application.port.out.CommuteOutPort;
import com.yeseung.commutecheck.modules.commute.domain.Commute;
import lombok.RequiredArgsConstructor;

@ServiceAdapter
@RequiredArgsConstructor
public class CommuteService implements CommuteUseCase {

    private final CommuteOutPort commuteOutPort;

    @Override
    public Commute commute(String accountId) {
        return commuteOutPort.commute(accountId);
    }

}
