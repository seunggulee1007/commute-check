package com.yeseung.commutecheck.common.advice.exceptions;

public class NotValidJwtTokenException extends RuntimeException {

    public NotValidJwtTokenException() {
        super("토믄이 유효하지 않습니다. 토큰을 다시 확인해 주세요.");
    }

}
