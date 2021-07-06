package com.ykb.izinmodule.api;

import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.entity.Employee;
import com.ykb.izinmodule.domain.mapper.EmployeeMapper;
import com.ykb.izinmodule.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.ykb.izinmodule.common.Constant.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = BASE + EMPLOYEE_PATH)
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Optional<Employee> employee = employeeService.findById(employeeId);
        return employee.map(value -> ResponseEntity.status(HttpStatus.OK).
                body(EmployeeMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> creatEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

}
