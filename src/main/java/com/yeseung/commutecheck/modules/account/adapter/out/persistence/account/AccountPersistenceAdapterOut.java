package com.yeseung.commutecheck.modules.account.adapter.out.persistence.account;

import com.yeseung.commutecheck.common.advice.exceptions.AlreadyPresentAccountException;
import com.yeseung.commutecheck.common.advice.exceptions.NotValidAccountException;
import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.account.application.port.out.GetAccountOutPort;
import com.yeseung.commutecheck.modules.account.application.port.out.RegisterAccountOutPort;
import com.yeseung.commutecheck.modules.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 계정 JPA 용 어댑터
 * JPA 관련 로직들을 해당 클래스에 작성한다.
 *
 * @see AccountEntity
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapterOut implements GetAccountOutPort, RegisterAccountOutPort {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 식별자와 비밀번호가 일치하는 회원 조회
     * 시큐리티에서 인증시 사용하기 때문에 인덱스인 회원 아이디로 조회 ( 인증 때문에 빈번하게 조회 됨으로 인덱스 사용 )
     *
     * @param accountId 회원 식별자
     * @param password  비밀번호
     * @return 조회된 회원
     */
    @Override public Account findByAccountIdAndPassword(Long accountId, String password) {
        return accountRepository.findById(accountId).orElseThrow(NotValidAccountException::new).mapToDomain();
    }

    /**
     * 사용자 등록
     * 데이터 베이스에 사용자를 등록시킨 이후 도메인으로 변환해서 반환한다.
     *
     * @param account 사용자 도메인
     * @return 등록된 사용자 도메인
     */
    @Override
    public Account registerAccount(Account account) {
        accountRepository.findByEmail(account.email()).ifPresent(c -> {
            throw new AlreadyPresentAccountException("이미 존재하는 계정입니다.");
        });
        AccountEntity accountEntity = AccountEntity.createNewAccount(account, passwordEncoder);
        accountRepository.save(accountEntity);
        return accountEntity.mapToDomain();
    }

}
