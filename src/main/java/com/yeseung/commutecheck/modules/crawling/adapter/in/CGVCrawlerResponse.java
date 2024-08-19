package com.yeseung.commutecheck.modules.crawling.adapter.in;

import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class CGVCrawlerResponse {

    private String movieId;

    /**
     * 수집 일자
     */
    private String collectDate;

    /**
     * 영화 순위
     */
    private String rank;

    /**
     * 영화 제목
     */
    private String title;

    /**
     * 영화 예매율
     */
    private String bookingRate;

    /**
     * 영화 개봉일
     */
    private String openDate;

    /**
     * 영화 자세히 보기 페이지 링크
     */
    private String detailLink;

    /**
     * 영화 포스터 이미지 링크
     */
    private String posterImageLink;

    /**
     * 개봉일
     */
    private String releaseDate;

    /**
     * 영화 예약 링크
     */
    private String bookingLink;

    /**
     * 등록 일시
     */
    private LocalDateTime createdAt;

    public static CGVCrawlerResponse mapToResponse(Movie movie) {
        CGVCrawlerResponse response = new CGVCrawlerResponse();
        response.movieId = movie.movieId();
        response.setCollectDate(movie.collectDate());
        response.setRank(movie.rank());
        response.setTitle(movie.title());
        response.setBookingRate(movie.bookingRate());
        response.setOpenDate(movie.openDate());
        response.setDetailLink(movie.detailLink());
        response.setPosterImageLink(movie.posterImageLink());
        response.setReleaseDate(movie.releaseDate());
        response.setBookingLink(movie.bookingLink());
        response.setCreatedAt(movie.createdAt());
        return response;
    }

}
