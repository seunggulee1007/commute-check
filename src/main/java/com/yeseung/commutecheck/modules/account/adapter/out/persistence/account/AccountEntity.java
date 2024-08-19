package com.yeseung.commutecheck.modules.account.adapter.out.persistence.account;

import com.yeseung.commutecheck.common.advice.exceptions.NotValidAccountException;
import com.yeseung.commutecheck.modules.account.adapter.out.persistence.UpdatedEntity;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.AccountId;
import com.yeseung.commutecheck.modules.account.domain.AccountRole;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "account")
class AccountEntity extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID accountId;

    /**
     * 이름
     */
    private String nickname;

    /**
     * 사번
     */
    private String empNumber;

    /**
     * 이메일
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * 비밀번호
     */
    private String password;

    /* 권한 */
    @ElementCollection(fetch = LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "id"))
    private Set<AccountRole> roles = Set.of(AccountRole.USER);

    public static AccountEntity createNewAccount(Account account, PasswordEncoder passwordEncoder) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.accountId = UUID.randomUUID();
        accountEntity.email = account.email();
        accountEntity.nickname = account.nickname();
        accountEntity.password = passwordEncoder.encode(account.password());
        accountEntity.roles = account.roles();
        accountEntity.empNumber = account.empNumber();
        return accountEntity;
    }

    public Account mapToDomain() {
        return Account.builder()
            .id(this.id)
            .accountId(AccountId.of(this.accountId.toString()))
            .email(this.email)
            .nickname(this.nickname)
            .empNumber(this.empNumber)
            .roles(this.roles)
            .build();
    }

    public void login(PasswordEncoder passwordEncoder, String credential) {
        if (!passwordEncoder.matches(credential, this.password)) {
            throw new NotValidAccountException("계정이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
        }
    }

}
