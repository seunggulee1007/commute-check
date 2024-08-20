package com.yeseung.commutecheck.common.advice;

import com.yeseung.commutecheck.common.advice.exceptions.AlreadyPresentAccountException;
import com.yeseung.commutecheck.common.advice.exceptions.NotMatchedPasswordException;
import com.yeseung.commutecheck.common.advice.exceptions.NotValidAccountException;
import com.yeseung.commutecheck.common.utils.ApiUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.yeseung.commutecheck.common.utils.ApiUtil.fail;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiUtil.ApiResult<Void> defaultException(Exception e) {
        e.printStackTrace();
        log.error("e :: {}, message :: {}", e.getClass().getName(), e.getMessage());
        return fail(e, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
        AlreadyPresentAccountException.class,
        NotMatchedPasswordException.class,
        NotValidAccountException.class,
        ConstraintViolationException.class,
    })
    @ResponseStatus(BAD_REQUEST)
    public ApiUtil.ApiResult<Void> badRequest(Exception e) {
        log.error("e :: {}, message :: {}", e.getClass().getName(), e.getMessage());
        return fail(e, BAD_REQUEST);
    }

}
