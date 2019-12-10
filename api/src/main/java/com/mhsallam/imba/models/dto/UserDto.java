package com.mhsallam.imba.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter@Getter@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Date created;
    private String avatar;
    private List<String> roles;

    public UserDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}