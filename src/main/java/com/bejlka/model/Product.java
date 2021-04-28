package com.bejlka.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String name;
    private Integer price;
    private String country;
    private String description;
}
