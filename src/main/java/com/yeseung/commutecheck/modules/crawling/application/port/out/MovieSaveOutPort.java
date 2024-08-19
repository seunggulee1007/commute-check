package com.yeseung.commutecheck.modules.crawling.application.port.out;

import com.yeseung.commutecheck.modules.crawling.domain.Movie;

import java.util.List;

public interface MovieSaveOutPort {

    List<Movie> saveMovie(List<Movie> movies);

}
