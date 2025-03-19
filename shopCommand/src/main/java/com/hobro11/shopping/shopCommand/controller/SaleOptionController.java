package com.hobro11.shopping.shopCommand.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.sales.SaleOptionStatus;
import com.hobro11.shopping.shopCommand.controller.form.SaleOptionCreateForm;
import com.hobro11.shopping.shopCommand.service.SaleOptionCommandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/saleOptions")
@RestController
@RequiredArgsConstructor
@Validated
public class SaleOptionController {

    private final SaleOptionCommandService saleOptionCommandService;

    @PostMapping
    public Long createSaleOption(@Valid @RequestBody SaleOptionCreateForm form) {
        return saleOptionCommandService.createSaleOption(form.toDto());
    }

    @PatchMapping("/{saleOptionId}/status")
    public void updateStatus(@PathVariable Long saleOptionId, @NotNull SaleOptionStatus status) {
        saleOptionCommandService.updateStatus(saleOptionId, status);
    }
}
