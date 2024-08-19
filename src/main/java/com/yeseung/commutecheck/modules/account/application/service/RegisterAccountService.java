package com.yeseung.commutecheck.modules.account.application.service;

import com.yeseung.commutecheck.common.annotations.UseCase;
import com.yeseung.commutecheck.modules.account.application.port.in.RegisterAccountCommand;
import com.yeseung.commutecheck.modules.account.application.port.in.RegisterAccountUseCase;
import com.yeseung.commutecheck.modules.account.application.port.out.RegisterAccountOutPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.RequiredArgsConstructor;

/**
 * 회원 등록 서비스
 *
 * @author seunggu.lee
 */
@UseCase
@RequiredArgsConstructor
public class RegisterAccountService implements RegisterAccountUseCase {

    private final RegisterAccountOutPort registerMemberOutPort;

    @Override
    public Account registerAccount(RegisterAccountCommand registerAccountCommand) {
        return registerMemberOutPort.registerAccount(registerAccountCommand.toDomain());
    }

}
