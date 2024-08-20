package com.yeseung.commutecheck.modules.account.adapter.out.jwt;

import com.yeseung.commutecheck.common.advice.exceptions.NotValidJwtTokenException;
import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.account.application.port.out.AuthenticationJwtPort;
import com.yeseung.commutecheck.modules.account.application.port.out.TokenRefreshPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.AccountRole;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class JwtAdapter implements AuthenticationJwtPort, TokenRefreshPort {

    private final Jwt jwt;

    public JwtToken authentication(Account account) {
        Jwt.Claims claims =
            Jwt.Claims.of(account.id(), account.accountId().getId(), account.email(), account.nickname(), account.roles().stream().map(
                AccountRole::name).toArray(String[]::new));
        return JwtToken.of(jwt.createAccessToken(claims), jwt.createRefreshToken(claims));
    }

    /**
     * refresh 토큰으로 신규 토큰 재발급
     *
     * @param refreshToken 갱신 토큰
     * @return JwtToken 신규 발급된 토큰
     */
    @Override
    public JwtToken refreshAccessToken(String refreshToken) {
        if (!jwt.validateToken(refreshToken)) {
            throw new NotValidJwtTokenException();
        }
        Jwt.Claims claims = jwt.verify(refreshToken);
        Jwt.Claims newClaims =
            Jwt.Claims.of(claims.getId(), claims.getAccountId(), claims.getEmail(), claims.getNickname(), claims.getRoles());
        return JwtToken.of(
            jwt.createAccessToken(newClaims),
            jwt.createRefreshToken(newClaims)
        );
    }

}
