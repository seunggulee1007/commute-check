package com.yeseung.commutecheck.modules.crawling.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Movie(
    // 영화 ID
    String movieId,
    //수집 일자
    String collectDate,

    // 영화 순위
    String rank,

    // 영화 제목
    String title,

    // 영화 예매율
    String bookingRate,

    // 영화 개봉일
    String openDate,

    // 영화 자세히 보기 페이지 링크
    String detailLink,

    // 영화 포스터 이미지 링크
    String posterImageLink,

    // 개봉일
    String releaseDate,

    // 영화 예약 링크
    String bookingLink,

    // 등록 일시
    LocalDateTime createdAt

) {

}
