package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.entity.Facility;
import com.projectbusan.bokjido.entity.FacilityReview;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Schema (description = "시설 리뷰 DTO")
public class FacilityReviewDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FacilityReviewRequestDTO {
        @Schema(description = "시설 아이디", example = "1")
        private Long facilityId;

        @Schema(description = "서비스 아이디", example = "1")
        private Long benefitId;

        @Schema(description = "청결도 별점", example = "5")
        private int clean;

        @Schema(description = "친절도 별점", example = "5")
        private int kindness;

        @Schema(description = "주차장 별점", example = "5")
        private int parking;

        @Schema(description = "내용", example = "본문 내용")
        @NotBlank(message = "리뷰 작성을 위해서 내용은 필요합니다.")
        private String content;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FacilityReviewResponseDTO {
        private Long id;
        private Long userId;
        private String content;
        private int clean;
        private int kindness;
        private int parking;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;

        @Builder
        public static FacilityReviewResponseDTO toDto(FacilityReview facilityReview) {
            return FacilityReviewResponseDTO.builder()
                    .id(facilityReview.getId())
                    .userId(facilityReview.getUser().getId())
            .content(facilityReview.getContent())
            .clean(facilityReview.getClean())
            .kindness(facilityReview.getKindness())
            .parking(facilityReview.getParking())
            .createAt(facilityReview.getCreatedAt())
            .modifiedAt(facilityReview.getModifiedAt())
                    .build();
        }
    }
}
