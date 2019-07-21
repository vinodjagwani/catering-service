package de.caterwings.catering.dto;

import lombok.Data;

@Data
public class CateringProductResponse {


    private String id;

    private String vendorUid;

    private String title;

    private String description;

    private Double price;

    private String image;

    private String dietaryFlag;

    private Integer views;
}
