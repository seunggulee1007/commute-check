package com.yeseung.commutecheck.modules.account.adapter.in.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {

    /**
     * 이메일
     */
    private String email;

    /**
     * 비밀번호
     */
    private String password;

}
