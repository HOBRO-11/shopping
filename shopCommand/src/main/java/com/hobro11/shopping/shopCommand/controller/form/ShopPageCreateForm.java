package com.hobro11.shopping.shopCommand.controller.form;

import java.net.URI;

import org.locationtech.jts.geom.Point;

import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShopPageCreateForm {
    
    @NotNull(message = "{shopPageCreateForm.memberId.notNull}")
    private final Long memberId;
    @NotBlank(message = "{shopPageCreateForm.title.notBlank}")
    private final String title;
    @NotBlank(message = "{shopPageCreateForm.description.notNull}")
    private final String description;
    @NotBlank(message = "{shopPageCreateForm.address.notNull}")
    private final String address;

    public ShopPageCreateDto toDto(URI thumbnailUri, Point location) {
        return new ShopPageCreateDto(memberId, title, thumbnailUri, description, location);
    }

}
