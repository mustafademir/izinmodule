package com.ykb.izinmodule.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected Date created = new Date();

    @Column(nullable = false)
    protected Date updated = new Date();

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        updated = new Date();
    }
}
