package com.yeseung.commutecheck.modules.account.application.port.out;

import com.yeseung.commutecheck.modules.account.domain.JwtToken;

public interface TokenRefreshPort {

    JwtToken refreshAccessToken(String refreshToken);

}
