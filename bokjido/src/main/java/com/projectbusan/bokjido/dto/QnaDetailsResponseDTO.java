package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaDetailsResponseDTO {
    private Long id;
    private String userId;
    private String title;
    private String content;
    private Long views;
    private List<CommentResponseDTO> comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public static QnaDetailsResponseDTO toDto(Qna qna, List<CommentResponseDTO> comments){
        return QnaDetailsResponseDTO.builder()
                .id(qna.getId())
                .userId(qna.getUser().getUserid())
                .title(qna.getTitle())
                .content(qna.getContent())
                .views(qna.getViews())
                .comments(comments)
                .createdAt(qna.getCreatedAt())
                .modifiedAt(qna.getModifiedAt())
                .build();
    }

    public static Page<QnaDetailsResponseDTO> toDtoList(Page<Qna> qnaPage){
        return qnaPage.map(m -> QnaDetailsResponseDTO.builder()
                .id(m.getId())
                .userId(m.getUser().getUserid())
                .title(m.getTitle())
                .content(m.getContent())
                .views(m.getViews())
                .comments(m.getComments().stream()
                        .map(CommentResponseDTO::toDto)
                        .collect(Collectors.toList()))
                .createdAt(m.getCreatedAt())
                .modifiedAt(m.getModifiedAt())
                .build());
    }
}
