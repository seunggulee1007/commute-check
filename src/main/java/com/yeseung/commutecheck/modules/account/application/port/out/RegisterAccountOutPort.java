package com.yeseung.commutecheck.modules.account.application.port.out;

import com.yeseung.commutecheck.modules.account.domain.Account;

/**
 * 회원 등록 용 out port
 */
public interface RegisterAccountOutPort {

    /**
     * 회원 등록
     *
     * @param Account 등록할 회원 정보
     * @return 등록된 회원 정보
     */
    Account registerAccount(Account Account);

}
