package com.hobro11.shopping.product.command;

import com.hobro11.shopping.product.command.dto.ProductCreateDto;

public interface ProductService {

    Long createProduct(ProductCreateDto productCreateDto);

    void updateName(Long id, String name);

    void updateDescription(Long id, String description);

    void updatePrice(Long id, Integer price);

    void deleteProduct(Long id);

}