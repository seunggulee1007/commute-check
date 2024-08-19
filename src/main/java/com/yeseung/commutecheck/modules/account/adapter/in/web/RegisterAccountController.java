package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.common.utils.ApiUtil;
import com.yeseung.commutecheck.modules.account.application.port.in.RegisterAccountUseCase;
import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yeseung.commutecheck.common.utils.ApiUtil.success;

/**
 * 회원 등록 컨트롤러
 *
 * @author seunggu.lee
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/auth")
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @PostMapping
    public ApiUtil.ApiResult<RegisteredAccount> registerAccount(@RequestBody RegisterAccountRequest registerAccountRequest) {
        Account account = registerAccountUseCase.registerAccount(registerAccountRequest.mapToCommand());
        return success(RegisteredAccount.mapToDto(account));
    }

}
