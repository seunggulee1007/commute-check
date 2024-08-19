package com.yeseung.commutecheck.modules.account.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 등록 컨트롤러
 *
 * @author seunggu.lee
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/auth")
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @PostMapping
    public ResponseEntity<RegisteredAccount> registerAccount(@RequestBody RegisterAccountRequest registerAccountRequest) {
        return ResponseEntity.ok(registerAccountUseCase.registerAccount(registerAccountRequest.mapToCommand()));
    }

}
