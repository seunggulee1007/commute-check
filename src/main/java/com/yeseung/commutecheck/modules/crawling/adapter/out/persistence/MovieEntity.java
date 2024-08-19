package com.yeseung.commutecheck.modules.crawling.adapter.out.persistence;

import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "movie", indexes = @Index(name = "idx_collect_date", columnList = "collectDate"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID movieId;

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
    @CreatedDate
    private LocalDateTime createdAt;

    public static MovieEntity from(Movie movie) {
        return MovieEntity.builder()
            .movieId(UUID.randomUUID())
            .collectDate(movie.collectDate())
            .rank(movie.rank())
            .title(movie.title())
            .bookingRate(movie.bookingRate())
            .openDate(movie.openDate())
            .detailLink(movie.detailLink())
            .posterImageLink(movie.posterImageLink())
            .releaseDate(movie.releaseDate())
            .bookingLink(movie.bookingLink())
            .build();
    }

    public Movie toDomain() {
        return Movie.builder()
            .movieId(this.movieId.toString())
            .collectDate(this.collectDate)
            .rank(this.rank)
            .title(this.title)
            .bookingRate(this.bookingRate)
            .openDate(this.openDate)
            .detailLink(this.detailLink)
            .posterImageLink(this.posterImageLink)
            .releaseDate(this.releaseDate)
            .bookingLink(this.bookingLink)
            .createdAt(this.createdAt)
            .build()
            ;
    }

}
