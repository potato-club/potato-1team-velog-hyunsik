package com.example.potato_velog_board.domain.repository;

import com.example.potato_velog_board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
