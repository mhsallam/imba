package com.mhsallam.imba.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity@Setter@Getter@NoArgsConstructor
public class Org {
   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   private String acronym;
   private String type;
   private String remarks;

   public Org(String name, String acronym, String type) {
      this.name = name;
      this.acronym = acronym;
      this.type = type;
   }
}
