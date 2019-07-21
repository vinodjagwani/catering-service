package de.caterwings.catering.constant;

import java.io.Serializable;

public enum DietaryFlagEnum implements Serializable {

    VEGAN("vegan"),
    LACTOSE_FREE("lactose-free");

    private final String code;

    DietaryFlagEnum(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
