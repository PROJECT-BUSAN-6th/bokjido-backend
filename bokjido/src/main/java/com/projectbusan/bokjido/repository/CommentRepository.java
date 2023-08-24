package com.projectbusan.bokjido.repository;

import com.projectbusan.bokjido.entity.Comment;
import com.projectbusan.bokjido.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByQna(Qna qna, Pageable pageable);
}
