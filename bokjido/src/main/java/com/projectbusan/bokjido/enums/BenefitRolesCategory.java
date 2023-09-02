package com.projectbusan.bokjido.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BenefitRolesCategory {
    CENTRAL_ROLE,
    LOCAL_ROLE,
    PRIVATE_SECTOR_ROLE;

    @JsonCreator
    public static BenefitRolesCategory from(String s){
        return BenefitRolesCategory.valueOf(s.toUpperCase());
    }

}
