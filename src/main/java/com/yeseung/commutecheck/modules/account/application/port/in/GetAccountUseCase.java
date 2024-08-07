package com.yeseung.commutecheck.modules.account.application.port.in;

import com.yeseung.commutecheck.modules.account.domain.Account;

public interface GetAccountUseCase {

    Account getAccount(GetAccountQuery accountQuery);

}
