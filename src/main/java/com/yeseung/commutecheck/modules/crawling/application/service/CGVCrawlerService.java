package com.yeseung.commutecheck.modules.crawling.application.service;

import com.yeseung.commutecheck.common.annotations.ServiceAdapter;
import com.yeseung.commutecheck.modules.crawling.application.port.in.CGVCrawlerUseCase;
import com.yeseung.commutecheck.modules.crawling.application.port.out.MovieSaveOutPort;
import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@ServiceAdapter
@RequiredArgsConstructor
public class CGVCrawlerService implements CGVCrawlerUseCase {

    private final CGVCrawler cgvCrawler;
    private final MovieSaveOutPort movieSaveOutPort;

    @Override
    public List<Movie> crawlCgvMovie() throws IOException {
        List<Movie> movies = cgvCrawler.getMovies();
        return movieSaveOutPort.saveMovie(movies);
    }

}
