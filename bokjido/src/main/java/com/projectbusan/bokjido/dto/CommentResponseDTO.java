package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {
    private Long id;
    private String userId;
    private String content;
    private String password;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public static CommentResponseDTO toDto(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .userId(comment.getUser().getUserid())
                .content(comment.getContent())
                .password(comment.getPassword())
                .isDeleted(comment.getIsDeleted())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public static Page<CommentResponseDTO> toDtoList(Page<Comment> commentPage){
        return commentPage.map(m -> CommentResponseDTO.builder()
                .id(m.getId())
                .userId(m.getUser().getUserid())
                .content(m.getContent())
                .password(m.getPassword())
                .isDeleted(m.getIsDeleted())
                .createdAt(m.getCreatedAt())
                .modifiedAt(m.getModifiedAt())
                .build());
    }
}
