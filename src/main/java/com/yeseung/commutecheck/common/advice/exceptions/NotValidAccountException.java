package com.yeseung.commutecheck.common.advice.exceptions;

public class NotValidAccountException extends RuntimeException {

    public NotValidAccountException() {
        super("일치하는 계정이 존재하지 않습니다.");
    }

    public NotValidAccountException(String message) {
        super(message);
    }

}
