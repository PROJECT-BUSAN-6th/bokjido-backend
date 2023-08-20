package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LifeCycleCategory {
    PREGNANCY_CHILDBIRTH,
    INFANT,
    CHILD,
    TEENAGER,
    YOUTH,
    MIDDLE_AGED,
    OLD_AGED;

    @JsonCreator
    public static LifeCycleCategory from(String s){
        return LifeCycleCategory.valueOf(s.toUpperCase());
    }
}
