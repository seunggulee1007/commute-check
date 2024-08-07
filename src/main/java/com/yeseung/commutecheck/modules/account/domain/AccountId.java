package com.yeseung.commutecheck.modules.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountId {

    private final String id;

    public static AccountId of(String id) {
        return new AccountId(id);
    }

    public static AccountId of(Long id) {
        return new AccountId(Long.toString(id));
    }

}
