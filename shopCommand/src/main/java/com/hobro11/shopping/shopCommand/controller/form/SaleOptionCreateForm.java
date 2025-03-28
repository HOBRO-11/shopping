package com.hobro11.shopping.shopCommand.controller.form;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOptionCreateForm {

    @NotBlank(message = "{saleOptionCreateForm.name.notBlank}")
    private String name;
    @NotNull(message = "{saleOptionCreateForm.shopPageId.notNull}")
    private Long shopPageId;
    @NotNull(message = "{saleOptionCreateForm.description.notNull}")
    private String description;
    @NotNull(message = "{saleOptionCreateForm.status.notNull}")
    private SaleOptionStatus status;
    @Positive(message = "{saleOptionCreateForm.price.positive}")
    @NotNull(message = "{saleOptionCreateForm.price.notNull}")
    private Integer price;

    public SaleOptionCreateDto toDto() {
        return new SaleOptionCreateDto(name, shopPageId, description, status, price);
    }
}
