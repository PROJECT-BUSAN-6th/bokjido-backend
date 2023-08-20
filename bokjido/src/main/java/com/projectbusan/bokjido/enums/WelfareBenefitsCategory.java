package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WelfareBenefitsCategory {
    WELFARE_FACILITIES,
    CHILDCARE_EDUCATIONAL_FACILITIES,
    PUBLIC_INSTITUTIONS,
    MEDICAL_INSTITUTIONS,
    CONVENIENCE_FACILITIES;

    @JsonCreator
    public static WelfareBenefitsCategory from(String s){
        return WelfareBenefitsCategory.valueOf(s.toUpperCase());
    }
}
