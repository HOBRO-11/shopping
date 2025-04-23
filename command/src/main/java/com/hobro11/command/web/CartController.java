package com.hobro11.command.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.command.service.CartCommandService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/carts")
@RestController
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartCommandService cartCommandService;
    private static final String AUTH_CHECK_EX = "@cartAuthHandler.check(#memberId)";

    @PatchMapping("/{memberId}/add")
    @PreAuthorize(AUTH_CHECK_EX)
    public void createCart(
            @PathVariable("memberId") final Long memberId,
            @NotNull @RequestParam("saleOptionId") final Long saleOptionId,
            @Positive @RequestParam("quantity") final Integer quantity) {
        cartCommandService.addSaleOptionAtCart(memberId, saleOptionId, quantity);
    }

    @PatchMapping("/{memberId}/remove")
    @PreAuthorize(AUTH_CHECK_EX)
    public void removeCart(
            @PathVariable("memberId") final Long memberId,
            @NotEmpty @RequestBody final List<Long> saleOptionIds) {
        cartCommandService.removeSaleOptionAtCart(memberId, saleOptionIds);
    }

    @DeleteMapping("/{memberId}")
    @PreAuthorize(AUTH_CHECK_EX)
    public void deleteCart(@PathVariable("memberId") final Long memberId) {
        cartCommandService.deleteCartByMemberId(memberId);
    }

}
