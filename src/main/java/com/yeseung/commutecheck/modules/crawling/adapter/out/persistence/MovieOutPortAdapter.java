package com.yeseung.commutecheck.modules.crawling.adapter.out.persistence;

import com.yeseung.commutecheck.common.annotations.PersistenceAdapter;
import com.yeseung.commutecheck.modules.crawling.application.port.out.MovieSaveOutPort;
import com.yeseung.commutecheck.modules.crawling.application.port.out.MovieSearchOutPort;
import com.yeseung.commutecheck.modules.crawling.domain.Movie;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MovieOutPortAdapter implements MovieSaveOutPort, MovieSearchOutPort {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> saveMovie(List<Movie> movies) {
        List<MovieEntity> movieEntities = movies.stream().map(MovieEntity::from).toList();
        movieRepository.saveAll(movieEntities);
        return movieEntities.stream().map(MovieEntity::toDomain).toList();
    }

    @Override
    public List<Movie> getMovieByDate(String date) {
        return movieRepository.findAllByCollectDate(date).stream().map(MovieEntity::toDomain).toList();
    }

}
