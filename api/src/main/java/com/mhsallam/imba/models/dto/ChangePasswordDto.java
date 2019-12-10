package com.mhsallam.imba.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class ChangePasswordDto {
    private String email;
    private String currentPassword;
    private String password;
}
