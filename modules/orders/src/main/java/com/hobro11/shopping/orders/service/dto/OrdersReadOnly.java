package com.hobro11.shopping.orders.service.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.OrdersStatus;

public interface OrdersReadOnly {
    Long getOrderNumber();

    Long getMemberId();

    Long getShopPageId();

    Set<OptionQuantity> getOptionQuantities();

    Long getPrice();

    Long getCheckSum();

    OrdersStatus getStatus();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
