package com.hobro11.shopping.shopCommand.controller.form;

import java.util.Set;

import com.hobro11.shopping.orders.OptionQuantity;
import com.hobro11.shopping.orders.service.dto.OrdersCreateDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersCreateForm {

    @NotNull(message = "{ordersCreateForm.memberId.notNull}")
    private Long memberId;
    @NotNull(message = "{ordersCreateForm.shopPageId.notNull}")
    private Long shopPageId;
    @NotEmpty(message = "{ordersCreateForm.optionQuantities.notEmpty}")
    private Set<OptionQuantity> optionQuantities;
    @Positive(message = "{ordersCreateForm.price.positive}")
    @NotNull(message = "{ordersCreateForm.price.notNull}")
    private Long price;

    public OrdersCreateDto toDto() {
        return new OrdersCreateDto(memberId, shopPageId, optionQuantities, price);
    }
}
