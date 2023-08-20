package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HouseholdSituationCategory {
    LOW_INCOME,
    DISABLED,
    SINGLE_PARENT_OR_GRANDSON,
    MULTIPLE_CHILDREN,
    MULTICULTURAL,
    NORTH_KOREAN_DEFECTORS,
    VETERANS;

    @JsonCreator
    public static HouseholdSituationCategory from(String s){
        return HouseholdSituationCategory.valueOf(s.toUpperCase());
    }
}
