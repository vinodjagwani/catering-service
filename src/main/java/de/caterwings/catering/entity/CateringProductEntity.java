package de.caterwings.catering.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("catering-product")
public class CateringProductEntity {


    @Id
    private String id;

    private String vendorUid;

    private String title;

    private String description;

    private Double price;

    private String image;

    private String dietaryFlag;

    private Integer views;

}
