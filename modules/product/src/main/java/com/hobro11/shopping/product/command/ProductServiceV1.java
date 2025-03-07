package com.hobro11.shopping.product.command;

import org.springframework.stereotype.Service;

import com.hobro11.shopping.product.Product;
import com.hobro11.shopping.product.command.dto.ProductCreateDto;
import com.hobro11.shopping.product.exception.ProductNotFoundException;
import com.hobro11.shopping.product.exception.ProductPriceException;
import com.hobro11.shopping.product.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceV1 implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public Long createProduct(ProductCreateDto productCreateDto) {
        uniqueNameCheck(productCreateDto.getName());
        return productRepo.save(productCreateDto.toEntity()).getId();
    }

    @Override
    public void updateName(Long id, String name) {
        uniqueNameCheck(name);
        Product product = getProduct(id);
        product.setName(name);
    }

    @Override
    public void updateDescription(Long id, String description) {
        Product product = getProduct(id);
        product.setDescription(description);
    }

    @Override
    public void updatePrice(Long id, Integer price) {
        if (price < 100) {
            throw new ProductPriceException();
        }

        Product product = getProduct(id);
        product.setPrice(price);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    private Product getProduct(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException());
    }

    private void uniqueNameCheck(String name) {
        productRepo.findByName(name).ifPresent(product -> {
            throw new ProductUniqueNameException();
        });
    }
}