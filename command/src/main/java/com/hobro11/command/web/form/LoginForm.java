package com.hobro11.command.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    
    @NotBlank(message = "{member.loginForm.username.notBlank}")
    private String username;
    
    @NotBlank(message = "{member.loginForm.password.notBlank}")
    private String password;
}
