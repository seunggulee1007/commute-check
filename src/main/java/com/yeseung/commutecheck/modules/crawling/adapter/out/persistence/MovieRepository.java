package com.yeseung.commutecheck.modules.crawling.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findAllByCollectDate(String date);

}
