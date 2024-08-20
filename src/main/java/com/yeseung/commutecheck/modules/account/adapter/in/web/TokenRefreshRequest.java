package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.modules.account.application.port.in.TokenRefreshCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 토큰 갱신 요청 객체
 *
 * @author seunggu.lee
 */
@Getter @Setter
@ToString
@EqualsAndHashCode
public class TokenRefreshRequest {

    /**
     * 갱신 토큰
     */
    private String refreshToken;

    public TokenRefreshCommand mapToCommand() {
        TokenRefreshCommand command = new TokenRefreshCommand();
        command.setRefreshToken(this.refreshToken);
        return command;
    }

}
