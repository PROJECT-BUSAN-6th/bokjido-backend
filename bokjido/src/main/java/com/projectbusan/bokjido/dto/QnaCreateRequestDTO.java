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
public class QnaCreateRequestDTO {
    @NotBlank(message = "제목이 필요합니다.")
    private String title;

    @NotBlank(message = "내용이 필요합니다.")
    private String content;

    private String password;
}
