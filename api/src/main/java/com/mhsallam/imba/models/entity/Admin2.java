package com.mhsallam.imba.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity@Setter@Getter@NoArgsConstructor
public class Admin2 {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;
    private String admin1Code;

    public Admin2(String name, String code, String admin1Code) {
        this.name = name;
        this.code = code;
        this.admin1Code = admin1Code;
    }
}