package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BenefitsCategory {
    WELFARE_FACILITIES,
    CHILDCARE_EDUCATIONAL_FACILITIES,
    PUBLIC_INSTITUTIONS,
    MEDICAL_INSTITUTIONS,
    CONVENIENCE_FACILITIES;

    @JsonCreator
    public static BenefitsCategory from(String s){
        return BenefitsCategory.valueOf(s.toUpperCase());
    }
}
