package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.CommentCreateRequestDTO;
import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.entity.*;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;


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
        return CommentResponseDTO.toDtoList(commentPage);
    }

    @Override
    public Page<CommentResponseDTO> getCommentByUser(User user, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findByUser(user, pageable);
        return CommentResponseDTO.toDtoList(commentPage);
    }

    @Override
    public Long deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);
        User getUser = findUser(comment.getUser().getId());
        if(!getUser.equals(user)){
            throw new CustomException(ErrorCode.PERMISSION_DENIED_FOR_DELETION, "본인이 작성한 게시물이 아니므로, 삭제 권한이 없습니다.");
        }
        commentRepository.delete(comment);
        return comment.getId();
    }

    private User findUser(Long id){
        return userRepository.findById(id).orElseThrow(()
                -> new CustomException(ErrorCode.USER_NOT_FOUND_ERROR, "해당 사용자는 존재하지 않습니다."));
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
