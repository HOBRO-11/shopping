package com.hobro11.shopping.shopCommand.controller.form;

import java.net.URI;

import com.hobro11.shopping.sales.Address;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopPageCreateForm {

    @NotNull(message = "{shopPageCreateForm.memberId.notNull}")
    private Long memberId;
    @NotBlank(message = "{shopPageCreateForm.title.notBlank}")
    private String title;
    @NotBlank(message = "{shopPageCreateForm.description.notNull}")
    private String description;
    @NotNull(message = "{shopPageCreateForm.address.notNull}")
    private Address address;
    @NotNull(message = "{shopPageCreateForm.zoneNo.notNull}")
    private Integer zoneNo;

    public ShopPageCreateDto toDto(URI thumbnailUri) {
        return new ShopPageCreateDto(memberId, title, thumbnailUri, description, address, zoneNo);
    }

}
