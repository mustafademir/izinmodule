package com.ykb.izinmodule.services.impl;

import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.entity.Employee;
import com.ykb.izinmodule.domain.mapper.EmployeeMapper;
import com.ykb.izinmodule.repositories.EmployeeRepository;
import com.ykb.izinmodule.services.IEmployeeService;
import com.ykb.izinmodule.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(EmployeeMapper.toEntity(employeeDTO));
        int yearRange = DateUtil.getDiffYears(employee.getStartedDate(),new Date());
        if (yearRange < 1) {
            employee.setAdvanceIzinNumber(5L);
            employee.setDeservedIzinNumber(0L);
        } else if(yearRange >= 1 && yearRange <= 5) {
            employee.setDeservedIzinNumber(15L);
        } else if(yearRange > 5 && yearRange <= 10) {
            employee.setDeservedIzinNumber(18L);
        } else if(yearRange > 10) {
            employee.setDeservedIzinNumber(24L);
        }
        employeeRepository.save(employee);
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }


}
