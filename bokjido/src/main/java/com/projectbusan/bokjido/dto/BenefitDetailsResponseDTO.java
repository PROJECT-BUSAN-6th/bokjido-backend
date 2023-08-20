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
public class BenefitDetailsResponseDTO {
    private Long id;
    private String name;
    private String applyTarget;
    private String content;
    private String howToApply;

    @Builder
    public static BenefitDetailsResponseDTO toDto(Benefit benefit) {
        return BenefitDetailsResponseDTO.builder()
                .id(benefit.getId())
                .name(benefit.getName())
                .applyTarget(benefit.getApplyTarget())
                .content(benefit.getSummary())
                .howToApply(benefit.getHowToApply())
                .build();
    }
}
