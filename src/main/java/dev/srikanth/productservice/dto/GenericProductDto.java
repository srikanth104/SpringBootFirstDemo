package dev.srikanth.productservice.dto;

import dev.srikanth.productservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
