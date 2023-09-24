package dev.srikanth.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    //CascadeType.PERSIST - Save without price also
    @OneToOne(cascade = CascadeType.PERSIST)
    private Price price;
    //                 P : C
    // Left to right : 1 : 1 (One product has one category )
    // Right to left : m : 1 (One category can have multiple products)
    // Ans m : 1
    // Now relation is many to one
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


}
