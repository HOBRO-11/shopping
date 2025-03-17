package com.hobro11.shopping.orders.service.dto;

import java.util.Set;

import com.hobro11.shopping.orders.OptionQuantity;

public interface CartReadOnly {

    Long getId();
    Long getMemberId();
    Set<OptionQuantity> getOptionQuantities();

}
