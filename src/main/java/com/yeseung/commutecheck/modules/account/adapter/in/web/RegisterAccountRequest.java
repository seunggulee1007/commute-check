package com.yeseung.commutecheck.modules.account.adapter.in.web;

import com.yeseung.commutecheck.modules.account.application.port.in.RegisterAccountCommand;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

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

    /**
     * 사번
     */
    private String empNumber;
    
    public RegisterAccountCommand mapToCommand() {
        RegisterAccountCommand registerAccountCommand = new RegisterAccountCommand();
        copyProperties(this, registerAccountCommand);
        registerAccountCommand.validateSelf();
        return registerAccountCommand;
    }

}
