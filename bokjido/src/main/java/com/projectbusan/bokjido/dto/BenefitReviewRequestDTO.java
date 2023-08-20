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
public class BenefitReviewRequestDTO {
    @NotBlank(message = "복지 서비스 리뷰를 작성하기 위해서 서비스 아이디는 필요합니다.")
    private Long serviceId;

    private Long facilityId;

    @NotBlank(message = "복지 서비스 리뷰를 작성하기 위해서 제목은 필요합니다.")
    private String title;

    @NotBlank(message = "복지 서비스 리뷰를 작성하기 위해서 내용은 필요합니다.")
    private String content;
}
