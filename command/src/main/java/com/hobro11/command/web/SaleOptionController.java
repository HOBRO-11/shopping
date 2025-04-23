package com.hobro11.command.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.command.domain.shop.SaleOptionStatus;
import com.hobro11.command.service.SaleOptionCommandService;
import com.hobro11.command.web.form.SaleOptionCreateForm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/saleOptions")
@RestController
@RequiredArgsConstructor
@Validated
public class SaleOptionController {

    private final SaleOptionCommandService saleOptionCommandService;
    private static final String AUTH_CHECK_EX = "@saleOptionAuthHandler.check(#saleOptionId)";

    @PostMapping
    @PreAuthorize(AUTH_CHECK_EX)
    public Long createSaleOption(@Valid @RequestBody final SaleOptionCreateForm form) {
        return saleOptionCommandService.createSaleOption(form.toDto());
    }

    @PatchMapping("/{saleOptionId}/status")
    @PreAuthorize(AUTH_CHECK_EX)
    public void updateStatus(
            @PathVariable("saleOptionId") final Long saleOptionId,
            @NotNull @RequestParam("status") final SaleOptionStatus status) {
        saleOptionCommandService.updateStatus(saleOptionId, status);
    }
}
