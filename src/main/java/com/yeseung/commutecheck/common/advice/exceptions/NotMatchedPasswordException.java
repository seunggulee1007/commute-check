package com.yeseung.commutecheck.common.advice.exceptions;

public class NotMatchedPasswordException extends RuntimeException {

    public NotMatchedPasswordException(String message) {
        super(message);
    }

}
