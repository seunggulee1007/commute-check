package com.yeseung.commutecheck.modules.account.application.port.in;

import com.yeseung.commutecheck.common.SelfValidating;
import com.yeseung.commutecheck.common.advice.exceptions.NotMatchedPasswordException;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.AccountRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.util.Set;

@Setter
public class RegisterAccountCommand extends SelfValidating<RegisterAccountCommand> {

    @NotNull
    private String nickname;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    /**
     * 사번
     */
    private String empNumber;

    public Account toDomain() {
        if (!password.equals(confirmPassword)) {
            throw new NotMatchedPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return Account.builder()
            .nickname(this.nickname)
            .password(this.password)
            .email(this.email)
            .empNumber(this.empNumber)
            .roles(Set.of(AccountRole.USER))
            .build();
    }

}
