package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InterestTopicCategory {
    PHYSICAL_HEALTH,
    LIFE_SUPPORT,
    JOB,
    SAFETY_OR_CRISIS,
    CHILDCARE,
    ADOPTION_OR_FOSTER,
    MICROFINANCE,
    MENTAL_HEALTH,
    DWELLING,
    CULTURE_OR_LEISURE,
    PREGNANCY_OR_CHILDBIRTH,
    EDUCATION,
    PROTECTION_OR_CARE,
    LAW;

    @JsonCreator
    public static InterestTopicCategory from(String s){
        return InterestTopicCategory.valueOf(s.toUpperCase());
    }
}
