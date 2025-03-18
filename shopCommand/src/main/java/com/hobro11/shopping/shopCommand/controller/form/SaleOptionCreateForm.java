package com.hobro11.shopping.shopCommand.controller.form;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaleOptionCreateForm {

    @NotBlank(message = "{saleOptionCreateForm.name.notBlank}")
    private final String name;
    @NotNull(message = "{saleOptionCreateForm.shopPageId.notNull}")
    private final Long shopPageId;
    @NotNull(message = "{saleOptionCreateForm.description.notNull}")
    private final String description;
    @NotNull(message = "{saleOptionCreateForm.status.notNull}")
    private final SaleOptionStatus status;
    @Positive(message = "{saleOptionCreateForm.price.positive}")
    @NotNull(message = "{saleOptionCreateForm.price.notNull}")
    private final Integer price;

    public SaleOptionCreateDto toDto() {
        return new SaleOptionCreateDto(name, shopPageId, description, status, price);
    }
}
