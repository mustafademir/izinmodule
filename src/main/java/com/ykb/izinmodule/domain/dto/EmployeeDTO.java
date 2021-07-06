package com.ykb.izinmodule.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeDTO {
    private String name;
    private String surName;
    private Date startedDate;
}
