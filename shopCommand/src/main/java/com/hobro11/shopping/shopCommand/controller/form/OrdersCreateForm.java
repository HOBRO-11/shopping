package com.hobro11.shopping.shopCommand.controller.form;

import java.util.Set;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrdersCreateForm {
    
    @NotNull(message = "{ordersCreateForm.memberId.notNull}")
    private final Long memberId;
    @NotNull(message = "{ordersCreateForm.shopPageId.notNull}")
    private final Long shopPageId;
    @NotEmpty(message = "{ordersCreateForm.optionQuantities.notEmpty}")
    private final Set<OptionQuantity> optionQuantities;
    @Positive(message = "{ordersCreateForm.price.positive}")
    @NotNull(message = "{ordersCreateForm.price.notNull}")
    private final Long price;

    public OrdersCreateDto toDto() {
        return new OrdersCreateDto(memberId, shopPageId, optionQuantities, price);
    }
}
