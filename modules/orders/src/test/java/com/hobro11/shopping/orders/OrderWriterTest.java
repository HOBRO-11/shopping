package com.hobro11.shopping.orders;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.orders.exception.OrdersCheckSumExceededException;
import com.hobro11.shopping.orders.repository.OrdersRepo;
import com.hobro11.shopping.orders.service.OrdersWriter;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;

@Transactional
@SpringBootTest(classes = OrdersTestConfig.class)
public class OrderWriterTest {

    @Autowired
    private OrdersWriter ordersWriter;

    @Autowired
    private OrdersRepo ordersRepo;

    private Long orderNumber;
    private Long memberId;
    private Long shopPageId;
    private Set<OptionQuantity> optionQuantities;
    private Long price;

    @BeforeEach
    public void setUp() {
        memberId = 1L;
        shopPageId = 1L;
        optionQuantities = Set.of(new OptionQuantity(1L, 1), new OptionQuantity(2L, 2));
        price = 100L;

        orderNumber = ordersWriter.createOrders(
                new OrdersCreateDto(memberId, shopPageId, optionQuantities, price));
    }

    @Test
    public void create() {
        ordersRepo.findById(orderNumber).ifPresent(
                orders -> {
                    assertThat(orders.getOrderNumber()).isEqualTo(orderNumber);
                    assertThat(orders.getMemberId()).isEqualTo(memberId);
                    assertThat(orders.getShopPageId()).isEqualTo(shopPageId);
                    assertThat(orders.getOptionQuantities()).isEqualTo(optionQuantities);
                    assertThat(orders.getPrice()).isEqualTo(price);
                    assertThat(orders.getCheckSum()).isEqualTo(price);
                    assertThat(orders.getStatus()).isEqualTo(OrdersStatus.IN_PAYMENT);
                });

    }

    @Test
    public void updateStatus() {
        ordersWriter.updateStatus(orderNumber, OrdersStatus.PAID);
        ordersRepo.findById(orderNumber).ifPresent(
                orders -> {
                    assertThat(orders.getStatus()).isEqualTo(OrdersStatus.PAID);
                });
    }

    @Test
    public void updateCheckSum() {
        Long refundPrice = 100L;
        ordersRepo.findById(orderNumber).ifPresent(orders -> {
            assertThrows(OrdersCheckSumExceededException.class, () -> {
                ordersWriter.updateCheckSum(orderNumber, refundPrice + price);
            });
        });

        ordersWriter.updateCheckSum(orderNumber, refundPrice);

        ordersRepo.findById(orderNumber).ifPresent(
                orders -> {
                    assertThat(orders.getCheckSum()).isEqualTo(price - refundPrice);
                });
    }

    @Test
    public void delete() {
        ordersWriter.deleteOrders(orderNumber);
        assertThat(ordersRepo.findById(orderNumber)).isEmpty();
    }
}
