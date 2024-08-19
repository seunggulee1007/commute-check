package com.yeseung.commutecheck.modules.crawling.application.port.in;

import com.yeseung.commutecheck.modules.crawling.domain.Movie;

import java.io.IOException;
import java.util.List;

public interface CGVCrawlerUseCase {

    List<Movie> crawlCgvMovie() throws IOException;

}
