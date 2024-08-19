package com.yeseung.commutecheck.modules.crawling.adapter.in;

import com.yeseung.commutecheck.modules.crawling.application.port.in.CGVCrawlerUseCase;
import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crawling")
public class CrawlingController {

    private final CGVCrawlerUseCase cgvCrawlerUseCase;

    @PostMapping
    public ResponseEntity<List<CGVCrawlerResponse>> crawlCgvMovie() throws IOException {
        // CGV 영화 순위 크롤링
        List<Movie> movies = cgvCrawlerUseCase.crawlCgvMovie();
        return ResponseEntity.ok(movies.stream().map(CGVCrawlerResponse::mapToResponse).toList());
    }

}
