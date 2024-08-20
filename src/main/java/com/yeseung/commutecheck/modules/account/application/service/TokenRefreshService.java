package com.yeseung.commutecheck.modules.account.application.service;

import com.yeseung.commutecheck.common.annotations.UseCase;
import com.yeseung.commutecheck.modules.account.application.port.in.TokenRefreshCommand;
import com.yeseung.commutecheck.modules.account.application.port.in.TokenRefreshUseCase;
import com.yeseung.commutecheck.modules.account.application.port.out.TokenRefreshPort;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class TokenRefreshService implements TokenRefreshUseCase {

    private final TokenRefreshPort tokenRefreshPort;

    @Override
    public JwtToken refreshAccessToken(TokenRefreshCommand command) {
        return tokenRefreshPort.refreshAccessToken(command.getRefreshToken());
    }

}
