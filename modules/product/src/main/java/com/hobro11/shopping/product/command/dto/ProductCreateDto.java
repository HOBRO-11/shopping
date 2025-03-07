package com.hobro11.shopping.product.command.dto;

import com.hobro11.shopping.product.Product;

import lombok.Data;

@Data
public class ProductCreateDto {

    private String name;

    private String description;

    private Integer price;

    public ProductCreateDto(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}
