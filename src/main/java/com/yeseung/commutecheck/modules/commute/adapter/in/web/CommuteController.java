package com.yeseung.commutecheck.modules.commute.adapter.in.web;

import com.yeseung.commutecheck.common.utils.ApiUtil;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.JwtAuthentication;
import com.yeseung.commutecheck.modules.commute.application.port.in.CommuteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yeseung.commutecheck.common.utils.ApiUtil.success;

/**
 * 출퇴근 컨트롤러
 *
 * @author seunggu.lee
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commute")
public class CommuteController {

    private final CommuteUseCase commuteUseCase;

    @PostMapping
    public ApiUtil.ApiResult<CommuteResponse> commute(@AuthenticationPrincipal JwtAuthentication authentication) {
        return success(CommuteResponse.from(commuteUseCase.commute(authentication.accountId())), "출근 처리 되었습니다.");
    }

}
