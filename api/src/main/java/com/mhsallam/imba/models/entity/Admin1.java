package com.mhsallam.imba.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity@Setter@Getter@NoArgsConstructor
public class Admin1 {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String code;

    public Admin1(String name, String code) {
        this.name = name;
        this.code = code;
    }
}