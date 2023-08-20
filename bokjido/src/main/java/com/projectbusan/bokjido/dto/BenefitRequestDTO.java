package com.projectbusan.bokjido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitRequestDTO {
    private String locality;
    private Integer age;
    private String keyword;
}
