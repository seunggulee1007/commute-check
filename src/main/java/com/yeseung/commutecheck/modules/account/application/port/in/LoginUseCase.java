package com.yeseung.commutecheck.modules.account.application.port.in;

import com.yeseung.commutecheck.modules.account.domain.JwtToken;

public interface LoginUseCase {

    JwtToken login(LoginCommand loginCommand);

}
