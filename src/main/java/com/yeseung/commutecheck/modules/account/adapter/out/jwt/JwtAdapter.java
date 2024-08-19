package com.yeseung.commutecheck.modules.account.adapter.out.jwt;

import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.account.application.port.out.AuthenticationJwtPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.AccountRole;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class JwtAdapter implements AuthenticationJwtPort {

    private final Jwt jwt;

    public JwtToken authentication(Account account) {
        Jwt.Claims claims =
            Jwt.Claims.of(account.id(), account.accountId().getId(), account.email(), account.nickname(), account.roles().stream().map(
                AccountRole::name).toArray(String[]::new));
        return JwtToken.of(jwt.createAccessToken(claims), jwt.createRefreshToken(claims));
    }

}
