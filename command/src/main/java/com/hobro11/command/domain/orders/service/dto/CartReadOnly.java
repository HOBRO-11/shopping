package com.hobro11.command.domain.orders.service.dto;

import java.util.Set;

import com.hobro11.command.domain.orders.OptionQuantity;

public interface CartReadOnly {

    Long getId();
    Long getMemberId();
    Set<OptionQuantity> getOptionQuantities();

}
