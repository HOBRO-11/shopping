package com.hobro11.command.web.form;

import com.hobro11.command.domain.members.MemberRole;
import com.hobro11.command.domain.members.service.dto.MemberCreateDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessMemberCreateForm {

    @NotNull(message = "{memberCreateForm.brn.notNull}")
    private Long brn;
    @NotBlank(message = "{memberCreateForm.name.notBlank}")
    private String name;
    @NotNull(message = "{memberCreateForm.phone.notNull}")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "{memberCreateForm.phone.pattern}")
    private String phone;

    public MemberCreateDto toDto() {
        return new MemberCreateDto(brn, name, phone, MemberRole.BUSINESS);
    }
}
