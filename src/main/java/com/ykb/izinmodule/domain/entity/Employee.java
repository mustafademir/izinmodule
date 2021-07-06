package com.ykb.izinmodule.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

   /*@OneToMany(mappedBy = "employee",
           cascade = CascadeType.ALL,
           orphanRemoval = true)
   private List<IzinRequest> izinRequests = new ArrayList<>();*/

}
