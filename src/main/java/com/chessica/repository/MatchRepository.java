package com.chessica.repository;


import com.chessica.domain.userRelated.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
