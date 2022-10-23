package com.serasaexperian.pessoa.repositories;

import com.serasaexperian.pessoa.models.ScoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreModel, UUID> {
    @Query(value = "SELECT * FROM TB_SCORE WHERE INICIAL <= ?1 AND FINAL >= ?1 LIMIT 1", nativeQuery = true)
    ScoreModel findOneByScoreRange(final Integer score);
}
