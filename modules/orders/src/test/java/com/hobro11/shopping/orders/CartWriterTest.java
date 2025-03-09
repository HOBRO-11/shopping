package com.hobro11.shopping.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.orders.repository.CartRepo;
import com.hobro11.shopping.orders.service.CartWriter;
import com.hobro11.shopping.orders.service.dto.CartCreateDto;

@Transactional
@SpringBootTest(classes = OrdersTestConfig.class)
public class CartWriterTest {

    @Autowired
    private CartWriter cartWriter;

    @Autowired
    private CartRepo cartRepo;

    private Long memberId;
    private Long cartId;

    @BeforeEach
    public void setUp() {
        memberId = 1L;

        cartId = cartWriter.createCart(new CartCreateDto(memberId));
        cartRepo.findById(cartId).ifPresent(cart -> {
            cart.addOptionQuantity(new OptionQuantity(1L, 1));
            cart.addOptionQuantity(new OptionQuantity(2L, 2));
        });
    }

    @Test
    public void create() {
        cartRepo.findById(cartId).ifPresent(cart -> {
            assert cart.getMemberId().equals(memberId);
            assert cart.getOptionQuantities().size() == 2;
        });
    }

    @Test
    public void addOptionQuantity() {
        cartWriter.addOptionQuantity(cartId, new OptionQuantity(3L, 3));
        cartRepo.findById(cartId).ifPresent(cart -> {
            assert cart.getOptionQuantities().size() == 3;
        });
    }

    @Test
    public void removeOptionQuantity() {
        cartWriter.removeOptionQuantity(cartId, new OptionQuantity(1L, 1));
        cartRepo.findById(cartId).ifPresent(cart -> {
            assert cart.getOptionQuantities().size() == 1;
        });
    }

    @Test
    public void updateOptionQuantity() {
        cartWriter.updateOptionQuantity(cartId, 1L, 10);
        cartRepo.findById(cartId).ifPresent(cart -> {
            assert cart.getOptionQuantities().size() == 2;
            // assert cart.getOptionQuantities()..get(new OptionQuantity(1L, 0)).getQuantity() == 10;
        });
    }

    @Test
    public void delete() {
        cartWriter.deleteCart(cartId);
        assert cartRepo.findById(cartId).isEmpty();
    }
}
