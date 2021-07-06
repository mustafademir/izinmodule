package com.ykb.izinmodule.domain.mapper;

import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public final class EmployeeMapper {

    private EmployeeMapper() {}

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return Employee.builder().name(employeeDTO.getName())
                .surName(employeeDTO.getSurName())
                .startedDate(employeeDTO.getStartedDate())
                .build();
    }

    public static EmployeeDTO toDto(Employee employee) {
        return EmployeeDTO.builder().name(employee.getName())
                .surName(employee.getSurName())
                .startedDate(employee.getStartedDate())
                .build();
    }
}
