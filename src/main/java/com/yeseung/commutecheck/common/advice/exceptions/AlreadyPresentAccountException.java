package com.yeseung.commutecheck.common.advice.exceptions;

/**
 * 이미 존재하는 사용자 예외
 *
 * @author seunggu.lee
 */
public class AlreadyPresentAccountException extends RuntimeException {

    public AlreadyPresentAccountException(String message) {
        super(message);
    }

}
