package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.kakaoinsurance.payment.application.port.in.member.RegisterMemberCommand;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 등록 요청 객체
 *
 * @author seunggu.lee
 */
@Getter
@Setter
public class RegisterAccountRequest {

    /**
     * 별명
     */
    private String nickname;
    /**
     * 이메일
     */
    private String email;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 비밀번호 확인
     */
    private String confirmPassword;

    public RegisterMemberCommand mapToCommand() {
        RegisterMemberCommand registerMemberCommand = new RegisterMemberCommand();
        copyProperties(this, registerMemberCommand);
        registerMemberCommand.validateSelf();
        return registerMemberCommand;
    }

}
