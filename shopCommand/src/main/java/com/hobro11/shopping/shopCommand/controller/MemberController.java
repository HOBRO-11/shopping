package com.hobro11.shopping.shopCommand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.members.MemberStatus;
import com.hobro11.shopping.shopCommand.controller.form.AdminMemberCreateForm;
import com.hobro11.shopping.shopCommand.controller.form.BasicMemberCreateForm;
import com.hobro11.shopping.shopCommand.controller.form.BusinessMemberCreateForm;
import com.hobro11.shopping.shopCommand.service.MemberCommandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/basic")
    public Long createBasicMember(@Valid @RequestBody BasicMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @PostMapping("/business")
    public Long createBusinessMember(@Valid @RequestBody BusinessMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @PostMapping("/admin")
    public Long createAdminMember(@Valid @RequestBody AdminMemberCreateForm form) {
        return memberCommandService.createMember(form.toDto());
    }

    @GetMapping("/brn")
    public String checkBrn(@NotNull Long brn) {
        memberCommandService.checkBrn(brn);
        return "ok";
    }

    @PatchMapping("/{memberId}/name")
    public void updateName(@PathVariable Long memberId, @NotBlank String name) {
        memberCommandService.updateName(memberId, name);
    }

    @PatchMapping("/{memberId}/phone")
    public void updatePhone(@PathVariable Long memberId,
            @Valid @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$") String phone) {
        memberCommandService.updatePhone(memberId, phone);
    }

    @PatchMapping("/{memberId}/status")
    public void updateStatus(@PathVariable Long memberId, @NotNull MemberStatus status) {
        memberCommandService.updateStatus(memberId, status);
    }
}
