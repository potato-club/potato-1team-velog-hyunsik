package com.velog.velogboard.repository;

import com.velog.velogboard.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardByUserId(String userId);
}
