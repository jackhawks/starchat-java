package com.starchat.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterDTO implements Serializable {
    @NotEmpty
    private String checkCodeKey;

    @NotBlank(message = "Email is not empty")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String checkCode;
}
