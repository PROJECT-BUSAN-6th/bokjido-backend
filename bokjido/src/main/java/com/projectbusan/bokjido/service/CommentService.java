package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.CommentCreateRequestDTO;
import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Long createComment(Long qnaId, User user, CommentCreateRequestDTO commentCreateRequestDto);
    Page<CommentResponseDTO> getComment(Long qnaId, Pageable pageable);
    Long deleteComment(Long commentId);
}
