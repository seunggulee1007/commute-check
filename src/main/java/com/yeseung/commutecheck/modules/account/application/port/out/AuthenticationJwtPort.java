package com.yeseung.commutecheck.modules.account.application.port.out;

import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;

public interface AuthenticationJwtPort {

    JwtToken authentication(Account account);

}
