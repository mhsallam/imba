package com.mhsallam.imba.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity@Setter@Getter@NoArgsConstructor
public class IdpHh {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private String spouseName;
    @NotNull
    private String phone;
    @NotNull
    private String identityNo;
    @NotNull
    private String identityType;
    @NotNull
    private Date registrationDate;
    private Date distributionDate;
    private int Girls;
    private int Boys;
    private int Men;
    private int Women;
    @NotNull
    private String originAdmin1Code;
    @NotNull
    private String displacementAdmin1Code;
    private String originAdmin2Code;
    private String displacementAdmin2Code;
    private String remarks;

    private Long registeredBy; // OrganisationId
}