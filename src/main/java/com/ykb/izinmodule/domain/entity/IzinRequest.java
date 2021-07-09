package com.ykb.izinmodule.domain.entity;

import com.ykb.izinmodule.enums.IzinRequestStatus;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Data
@Builder
@AllArgsConstructor
public class IzinRequest extends AbstractEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private IzinRequestStatus status;

    @Column
    private Long requestedIzinNumber;

    @ManyToOne
    private Employee employee;
}
