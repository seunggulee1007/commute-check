package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.common.utils.ApiUtil;
import com.yeseung.commutecheck.modules.account.application.port.in.LoginCommand;
import com.yeseung.commutecheck.modules.account.application.port.in.LoginUseCase;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yeseung.commutecheck.common.utils.ApiUtil.success;

/**
 * 사용자 로그인 컨트롤러
 * 로그인 후 JWT 토큰을 반환한다.
 *
 * @author seunggu.lee
 * @see JwtToken
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/auth/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ApiUtil.ApiResult<JwtToken> login(@RequestBody LoginRequest loginRequest) {
        return success(loginUseCase.login(LoginCommand.of(loginRequest.getEmail(), loginRequest.getPassword())));
    }

}
