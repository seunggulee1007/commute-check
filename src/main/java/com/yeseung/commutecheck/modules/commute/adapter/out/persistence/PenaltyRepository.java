package com.yeseung.commutecheck.modules.commute.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<PenaltyEntity, Long> {

    Optional<PenaltyEntity> findByAccountId(String accountId);

}
