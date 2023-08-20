package com.projectbusan.bokjido.dto;

import com.projectbusan.bokjido.entity.Benefit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitMainResponseDTO {
    private Long id;
    private String name;
    private String applyTarget;
    private String summary;

    @Builder
    public static BenefitMainResponseDTO toDto(Benefit benefit) {
        return BenefitMainResponseDTO.builder()
                .id(benefit.getId())
                .name(benefit.getName())
                .applyTarget(benefit.getApplyTarget())
                .summary(benefit.getSummary())
                .build();
    }
}
