package com.mhsallam.imba.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class OrgDto {
    private Long id;
    private String name;
    private String acronym;
    private String type;
    private String remarks;

    public OrgDto(String name, String acronym, String type) {
        this.name = name;
        this.acronym = acronym;
        this.type = type;
    }
}
