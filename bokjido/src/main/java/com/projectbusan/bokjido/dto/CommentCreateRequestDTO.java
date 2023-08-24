package com.projectbusan.bokjido.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateRequestDTO {
    @NotBlank(message = "댓글 내용이 필요합니다.")
    private String content;

    private String password;
}
