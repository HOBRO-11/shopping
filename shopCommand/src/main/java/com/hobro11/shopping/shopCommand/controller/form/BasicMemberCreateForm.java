package com.hobro11.shopping.shopCommand.controller.form;

import com.hobro11.shopping.members.MemberRole;
import com.hobro11.shopping.members.service.dto.MemberCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicMemberCreateForm {

    @NotBlank(message = "{memberCreateForm.name.notBlank}")
    private String name;
    @NotNull(message = "{memberCreateForm.phone.notNull}")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "{memberCreateForm.phone.pattern}")
    private String phone;

    public MemberCreateDto toDto() {
        return new MemberCreateDto(null, name, phone, MemberRole.BASIC);
    }

}
