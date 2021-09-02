package com.example.potato_velog_board.domain.repository;

import com.example.potato_velog_board.domain.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
