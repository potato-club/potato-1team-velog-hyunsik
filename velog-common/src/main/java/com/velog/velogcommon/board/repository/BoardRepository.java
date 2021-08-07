package com.velog.velogcommon.board.repository;

import com.velog.velogcommon.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardByUserId(String userId);
}
