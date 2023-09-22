package dev.srikanth.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    private double price;
    //                 P : C
    // Left to right : 1 : 1 (One product has one category )
    // Right to left : m : 1 (One category can have multiple products)
    // Ans m : 1
    // Now relation is many to one
    @ManyToOne
    private Category category;


}
