package dev.srikanth.productservice.model;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel {
    private String name;
}
