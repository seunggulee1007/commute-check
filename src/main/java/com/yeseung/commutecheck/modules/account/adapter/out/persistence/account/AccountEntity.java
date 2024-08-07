package com.yeseung.commutecheck.modules.account.adapter.out.persistence.account;

import com.yeseung.commutecheck.common.advice.exceptions.NotValidMemberException;
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
    private UUID memberId;

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

    public static AccountEntity createNewMember(Account account, PasswordEncoder passwordEncoder) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.memberId = UUID.randomUUID();
        accountEntity.email = account.email();
        accountEntity.nickname = account.nickname();
        accountEntity.password = passwordEncoder.encode(account.password());
        accountEntity.roles = account.roles();
        return accountEntity;
    }

    public Account mapToDomain() {
        return Account.builder()
            .id(this.id)
            .accountId(AccountId.of(this.memberId.toString()))
            .email(this.email)
            .nickname(this.nickname)
            .roles(this.roles)
            .build();
    }

    public void login(PasswordEncoder passwordEncoder, String credential) {
        if (!passwordEncoder.matches(credential, this.password)) {
            throw new NotValidMemberException("계정이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
        }
    }

}
