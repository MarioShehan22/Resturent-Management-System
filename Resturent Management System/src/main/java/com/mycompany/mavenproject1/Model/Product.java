package com.mycompany.mavenproject1.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String productName;
    private String category;
    private String description;
    private BigDecimal price;
    private String imagePath; 
    private int stockQuantity;
    private boolean active;
}
