package com.yeseung.commutecheck.modules.account.adapter.out.jwt;


import com.yeseung.commutecheck.modules.account.application.port.in.GetAccountQuery;
import com.yeseung.commutecheck.modules.account.application.port.in.GetAccountUseCase;
import com.yeseung.commutecheck.modules.account.domain.Account;
import com.yeseung.commutecheck.modules.account.domain.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 로그인 시 인증 객체를 Authentication 에 담아주는 클래스
 *
 * @author seunggu.lee
 * @see AuthenticationProvider#authenticate(Authentication)
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final GetAccountUseCase getAccountUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken)authentication;
        return processUserAuthentication((JwtAuthentication)authenticationToken.getPrincipal(), authenticationToken.getCredentials());
    }

    private Authentication processUserAuthentication(JwtAuthentication principal, CredentialInfo credential) {
        try {
            Account account = getAccount(principal.id(), credential);
            CredentialInfo credentialInfo = new CredentialInfo(account.password());
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                new JwtAuthentication(account.id(), account.accountId().getId(), account.email(), account.nickname()),
                credentialInfo,
                this.authorities(account.roles()));
            authenticationToken.setDetails(account);
            return authenticationToken;
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }

    private Account getAccount(Long accountId, CredentialInfo credential) {
        return getAccountUseCase.getAccount(GetAccountQuery.of(accountId, credential.getCredential()));
    }

    private Collection<? extends GrantedAuthority> authorities(Set<AccountRole> role) {
        return role.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
    }

}