package de.caterwings.catering.dto;

import de.caterwings.catering.constant.DietaryFlagEnum;
import de.caterwings.catering.validation.annotation.Enum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CateringProductRequest {


    @NotBlank(message = "{error.vendorUid.non_null_or_empty}")
    private String vendorUid;

    @NotBlank(message = "{error.title.non_null_or_empty}")
    private String title;

    @NotBlank(message = "{error.description.non_null_or_empty}")
    private String description;

    @NotNull(message = "{error.price.non_null}")
    private Double price;

    private String image;

    @Enum(enumClass = DietaryFlagEnum.class, ignoreCase = true)
    private String dietaryFlag;

    private Integer views;
}
