package com.yeseung.commutecheck.modules.account.adapter.out.persistence.account;

import com.yeseung.commutecheck.common.advice.exceptions.NotValidMemberException;
import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.account.application.port.out.GetAccountOutPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.RequiredArgsConstructor;

/**
 * 계정 JPA 용 어댑터
 * JPA 관련 로직들을 해당 클래스에 작성한다.
 *
 * @see AccountEntity
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapterOut implements GetAccountOutPort {

    private final AccountRepository accountRepository;


    /**
     * 회원 식별자와 비밀번호가 일치하는 회원 조회
     * 시큐리티에서 인증시 사용하기 때문에 인덱스인 회원 아이디로 조회 ( 인증 때문에 빈번하게 조회 됨으로 인덱스 사용 )
     *
     * @param accountId 회원 식별자
     * @param password 비밀번호
     * @return 조회된 회원
     */
    @Override public Account findByAccountIdAndPassword(Long accountId, String password) {
        return accountRepository.findById(accountId).orElseThrow(NotValidMemberException::new).mapToDomain();
    }

}
