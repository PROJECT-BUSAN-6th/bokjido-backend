package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.entity.BenefitReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitReviewResponseDTO {
    private Long id;
    private String userId;
    private Long facilityId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public static BenefitReviewResponseDTO toDto(BenefitReview benefitReview) {
        return BenefitReviewResponseDTO.builder()
                .id(benefitReview.getId())
                .userId(benefitReview.getUser().getUserid())
                .facilityId(benefitReview.getFacility().getId())
                .content(benefitReview.getContent())
                .createdAt(benefitReview.getCreatedAt())
                .modifiedAt(benefitReview.getModifiedAt())
                .build();
    }
}
