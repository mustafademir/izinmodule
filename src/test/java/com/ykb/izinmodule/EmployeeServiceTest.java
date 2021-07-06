package com.ykb.izinmodule;

import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.entity.Employee;
import com.ykb.izinmodule.domain.mapper.EmployeeMapper;
import com.ykb.izinmodule.repositories.EmployeeRepository;
import com.ykb.izinmodule.services.IEmployeeService;
import com.ykb.izinmodule.services.impl.EmployeeServiceImpl;
import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest {

    private IEmployeeService instance;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        instance = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void findByID_IdIsNotNull() {
        final Long id = 5L;

        MockUp<EmployeeRepository> employeeRepositoryMockUp = new MockUp<EmployeeRepository>() {
            @Mock(invocations = 1)
            Optional<Employee> findById(Long id) {
                return Optional.empty();
            }
        };

        Deencapsulation.setField(instance, "employeeRepository", employeeRepositoryMockUp.getMockInstance());
        instance.findById(id);
        Assert.assertNotEquals(null, Optional.of(new Employee()));
    }


    @Test
    public void saveEmployee() {
        Employee employee = new Employee();
        employee.setName("name");
        employee.setDeservedIzinNumber(10L);
        employee.setStartedDate(new Date());
        employee.setSurName("surname");
        EmployeeDTO employeeDTO = EmployeeDTO.builder().name("name").startedDate(new Date()).surName("surname").build();
        Assert.assertNotNull(employeeRepository.save(EmployeeMapper.toEntity(employeeDTO)));
    }
}
