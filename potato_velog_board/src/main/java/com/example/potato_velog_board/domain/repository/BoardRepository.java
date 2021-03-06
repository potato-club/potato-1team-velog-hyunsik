package com.example.potato_velog_board.domain.repository;

import com.example.potato_velog_board.domain.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndUuid(Long id, String uuid);

    List<Board> findByUuidOrderByIdDesc(String uuid);
}
