package com.yeseung.commutecheck.modules.account.application.service;

import com.yeseung.commutecheck.common.annotations.ServiceAdapter;
import com.yeseung.commutecheck.modules.account.application.port.out.GetAccountOutPort;
import com.yeseung.commutecheck.modules.account.application.port.in.GetAccountQuery;
import com.yeseung.commutecheck.modules.account.application.port.in.GetAccountUseCase;
import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.RequiredArgsConstructor;

@ServiceAdapter
@RequiredArgsConstructor
public class GetAccountService implements GetAccountUseCase {

    private final GetAccountOutPort getAccountAdapterPort;

    @Override
    public Account getAccount(GetAccountQuery accountQuery) {
        return getAccountAdapterPort.findByAccountIdAndPassword(accountQuery.getAccountId(), accountQuery.getPassword());
    }

}
