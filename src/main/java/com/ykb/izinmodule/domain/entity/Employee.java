package com.ykb.izinmodule.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AbstractEntity{

   @Column
   private String name;

   @Column
   private String surName;

   @Column(nullable = false)
   private Date startedDate;

   @Column
   private Long deservedIzinNumber;

   @Column
   private Long advanceIzinNumber;
}
