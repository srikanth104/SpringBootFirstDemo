package dev.srikanth.productservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    private double price;
    private Category category;


}
