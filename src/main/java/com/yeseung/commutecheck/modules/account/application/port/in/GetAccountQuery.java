package com.yeseung.commutecheck.modules.account.application.port.in;

import lombok.Getter;

@Getter
public class GetAccountQuery {

    private Long accountId;
    private String password;

    public static GetAccountQuery of(Long accountId, String password) {
        GetAccountQuery query = new GetAccountQuery();
        query.accountId = accountId;
        query.password = password;
        return query;
    }

}
