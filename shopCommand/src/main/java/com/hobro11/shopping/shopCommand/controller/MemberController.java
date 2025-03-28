package com.hobro11.shopping.shopCommand.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.shopCommand.controller.form.AdminMemberCreateForm;
import com.hobro11.shopping.shopCommand.controller.form.BasicMemberCreateForm;
import com.hobro11.shopping.shopCommand.controller.form.BusinessMemberCreateForm;
import com.hobro11.shopping.shopCommand.service.MemberCommandService;
import com.hobro11.shopping.shopCommand.validate.Phone;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/basic")
    public Long createBasicMember(@Valid @RequestBody final BasicMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @PostMapping("/business")
    public Long createBusinessMember(@Valid @RequestBody final BusinessMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @PostMapping("/admin")
    public Long createAdminMember(@Valid @RequestBody final AdminMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @GetMapping("/brn")
    public String checkBrn(@NotNull @RequestParam("brn") final Long brn) {
        memberCommandService.checkBrn(brn);
        return "ok";
    }

    @PatchMapping("/{memberId}/name")
    public void updateName(
            @PathVariable("memberId") final Long memberId,
            @NotBlank @RequestBody final String name) {
        memberCommandService.updateName(memberId, name);
    }

    @PatchMapping("/{memberId}/phone")
    public void updatePhone(
            @PathVariable("memberId") final Long memberId,
            @Phone @RequestBody final String phone) {
        memberCommandService.updatePhone(memberId, phone);
    }

    @PatchMapping("/{memberId}/status")
    public void updateStatus(
            @PathVariable("memberId") final Long memberId,
            @NotNull @RequestParam("status") final MemberStatus status) {
        memberCommandService.updateStatus(memberId, status);
    }
}
