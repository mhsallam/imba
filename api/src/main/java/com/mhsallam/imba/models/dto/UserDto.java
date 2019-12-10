package com.mhsallam.imba.models.dto;

import lombok.*;

import java.util.Collection;
import java.util.Date;

@Setter@Getter@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private Date created;
    private String avatar;
    private Collection<String> roles;

    public UserDto(String email, String name, Date created, String avatar, Collection<String> roles) {
        this.email = email;
        this.name = name;
        this.created = created;
        this.avatar = avatar;
        this.roles = roles;
    }
}