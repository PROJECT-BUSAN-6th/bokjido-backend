package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.CommentCreateRequestDTO;
import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.entity.*;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final QnaRepository qnaRepository;


    @Override
    public Long createComment(Long qnaId, User user, CommentCreateRequestDTO commentCreateRequestDto) {
        Comment comment = Comment.builder()
                .qna(findQna(qnaId))
                .user(user)
                .content(commentCreateRequestDto.getContent())
                .password(commentCreateRequestDto.getPassword())
                .isDeleted(false)
                .build();

        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public Page<CommentResponseDTO> getComment(Long qnaId, Pageable pageable) {
        Qna qna = findQna(qnaId);
        Page<Comment> commentPage = commentRepository.findByQna(qna, pageable);
        List<CommentResponseDTO> commentDTOs = commentPage.getContent().stream()
                .map(CommentResponseDTO::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(commentDTOs, pageable, commentPage.getTotalElements());
    }

    @Override
    public Long deleteComment(Long commentId) {
        Comment comment = findComment(commentId);
        commentRepository.delete(comment);
        return comment.getId();
    }


    private Qna findQna(Long id){
        return qnaRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.QNA_NOT_FOUND_ERROR, "해당 질의응답 게시물은 존재하지 않습니다."));
    }

    private Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.COMMENT_NOT_FOUND_ERROR, "해당 질의응답 댓글은 존재하지 않습니다."));
    }
}
