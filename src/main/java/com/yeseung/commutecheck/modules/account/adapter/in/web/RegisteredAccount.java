package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.Data;

@Data
public class RegisteredAccount {

    private String accountId;

    /**
     * 닉네임
     */
    private String nickname;

    /**
     * 이메일
     */
    private String email;

    public static RegisteredAccount mapToDto(Account account) {
        RegisteredAccount registeredMember = new RegisteredAccount();
        registeredMember.accountId = account.accountId().getId();
        registeredMember.nickname = account.nickname();
        registeredMember.email = account.email();
        return registeredMember;
    }

}
