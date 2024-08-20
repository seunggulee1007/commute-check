package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.common.utils.ApiUtil;
import com.yeseung.commutecheck.modules.account.application.port.in.TokenRefreshUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yeseung.commutecheck.common.utils.ApiUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/auth/token")
public class TokenController {

    private final TokenRefreshUseCase useCase;

    @PutMapping("/refresh")
    public ApiUtil.ApiResult<TokenRefreshResponse> refreshAccessTokenByRefreshToken(@RequestBody TokenRefreshRequest request) {
        return success(TokenRefreshResponse.from(useCase.refreshAccessToken(request.mapToCommand())));
    }

}
