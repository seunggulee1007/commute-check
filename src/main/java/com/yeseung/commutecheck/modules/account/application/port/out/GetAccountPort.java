package com.yeseung.commutecheck.modules.account.application.port.out;

import com.yeseung.commutecheck.modules.account.domain.Account;

public interface GetAccountPort {

    /**
     * 이메일과 비밀번호가 일치하는 회원 조회
     * 로그인 시 이메일로 로그인 하기 때문에 해당 메서드를 사용한다.
     *
     * @param email    이메일
     * @param password 비밀번호
     * @return 조회된 회원
     */
    Account getAccountByEmailAndPassword(String email, String password);

}
