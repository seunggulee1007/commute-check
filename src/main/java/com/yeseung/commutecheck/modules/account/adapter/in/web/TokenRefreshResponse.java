package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.Getter;

@Getter
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;

    public static TokenRefreshResponse from(JwtToken token) {
        TokenRefreshResponse response = new TokenRefreshResponse();
        response.accessToken = token.getAccessToken();
        response.refreshToken = token.getRefreshToken();
        return response;
    }

}
