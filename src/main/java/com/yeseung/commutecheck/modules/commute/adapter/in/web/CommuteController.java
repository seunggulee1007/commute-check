package com.yeseung.commutecheck.modules.commute.adapter.in.web;

import com.yeseung.commutecheck.common.utils.ApiUtil;
import com.yeseung.commutecheck.modules.account.adapter.out.jwt.JwtAuthentication;
import com.yeseung.commutecheck.modules.commute.application.port.in.CommuteUseCase;
import com.yeseung.commutecheck.modules.commute.application.port.in.PenaltyUseCase;
import com.yeseung.commutecheck.modules.commute.domain.Commute;
import com.yeseung.commutecheck.modules.commute.domain.Penalty;
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
    private final PenaltyUseCase penaltyUseCase;

    @PostMapping
    public ApiUtil.ApiResult<CommuteResponse> commute(@AuthenticationPrincipal JwtAuthentication authentication) {
        Commute commute = commuteUseCase.commute(authentication.accountId());
        CommuteResponse commuteResponse = CommuteResponse.from(commute);
        if (commute.late()) {
            Penalty penalty = penaltyUseCase.updatePenalty(authentication.accountId());
            commuteResponse.updatePenalty(penalty);
        }
        return success(commuteResponse, "출근 처리 되었습니다.");
    }

}
