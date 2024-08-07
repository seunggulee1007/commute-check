package com.yeseung.commutecheck.modules.account.application.port.out;

import com.yeseung.commutecheck.modules.account.domain.Account;

public interface GetAccountOutPort {

    Account findByAccountIdAndPassword(Long accountId, String password);

}
