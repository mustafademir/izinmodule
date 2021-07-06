package com.ykb.izinmodule.services;

import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.entity.Employee;

import java.util.Optional;

public interface IEmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    Optional<Employee> findById(Long id);
}
