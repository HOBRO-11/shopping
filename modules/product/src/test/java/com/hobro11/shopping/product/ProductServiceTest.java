package com.hobro11.shopping.product;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.product.command.ProductService;
import com.hobro11.shopping.product.command.ProductUniqueNameException;
import com.hobro11.shopping.product.command.dto.ProductCreateDto;
import com.hobro11.shopping.product.exception.ProductPriceException;
import com.hobro11.shopping.product.repository.ProductRepo;

@SpringBootTest(classes = ProductTestConfig.class)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void create() {
        Long id = productService.createProduct(new ProductCreateDto("testName", "testDesc", 10));
        Product findEntity = productRepo.findById(id).get();
        
        assertThat(findEntity.getName()).isEqualTo("testName");
        assertThat(findEntity.getDescription()).isEqualTo("testDesc");
        assertThat(findEntity.getPrice()).isEqualTo(10);
    }

    @Test
    public void createDuplicateName() throws Throwable {
        Executable ex = () -> productService
                .createProduct(new ProductCreateDto("testName", "testDesc", 10));

        ex.execute();
        assertThrows(ProductUniqueNameException.class,
                () -> ex.execute());
    }

    @Test
    public void update() {
        Long id = productService.createProduct(new ProductCreateDto("testName", "testDesc", 10));

        assertThrows(ProductUniqueNameException.class,
                () -> productService.updateName(id, "testName"));
        productService.updateName(id, "newTestName");

        assertThrows(ProductPriceException.class,
                () -> productService.updatePrice(id, 99));
        productService.updatePrice(id, 100);

        productService.updateDescription(id, "newTestDescription");
    }

    @Test
    public void delete() {
        Long id = productService.createProduct(new ProductCreateDto("testName", "testDesc", 10));
        productService.deleteProduct(id);
        assertThat(productRepo.findById(id)).isEmpty();
    }

}
