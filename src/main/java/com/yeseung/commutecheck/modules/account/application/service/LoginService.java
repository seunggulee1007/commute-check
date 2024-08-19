package com.yeseung.commutecheck.modules.account.application.service;

import com.yeseung.commutecheck.common.annotations.UseCase;
import com.yeseung.commutecheck.modules.account.application.port.in.LoginCommand;
import com.yeseung.commutecheck.modules.account.application.port.in.LoginUseCase;
import com.yeseung.commutecheck.modules.account.application.port.out.AuthenticationJwtPort;
import com.yeseung.commutecheck.modules.account.application.port.out.GetAccountPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 로그인을 구현한 객체
 * 로그인이 완료되면 유효한 토큰이 발행된다.
 *
 * @author seunggu.lee
 */
@UseCase
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final GetAccountPort getAccountPort;
    private final AuthenticationJwtPort authenticationJwtPort;

    @Override
    @Transactional(readOnly = true)
    public JwtToken login(LoginCommand loginCommand) {
        Account account =
            getAccountPort.getAccountByEmailAndPassword(loginCommand.getEmail(), loginCommand.getPassword());
        return authenticationJwtPort.authentication(account);
    }

}
