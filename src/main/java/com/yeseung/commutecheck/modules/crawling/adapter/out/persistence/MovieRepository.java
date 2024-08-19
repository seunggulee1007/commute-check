package com.yeseung.commutecheck.modules.crawling.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
