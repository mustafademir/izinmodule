package com.ykb.izinmodule;

import com.ykb.izinmodule.common.http.RestException;
import com.ykb.izinmodule.domain.dto.EmployeeDTO;
import com.ykb.izinmodule.domain.dto.RequestDTO;
import com.ykb.izinmodule.domain.entity.Employee;
import com.ykb.izinmodule.domain.mapper.EmployeeMapper;
import com.ykb.izinmodule.repositories.EmployeeRepository;
import com.ykb.izinmodule.repositories.IzinRequestRepositoy;
import com.ykb.izinmodule.services.IzinRequestService;
import com.ykb.izinmodule.services.impl.IzinRequestServiceImpl;
import com.ykb.izinmodule.util.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IzinRequestServiceTest {
    private IzinRequestService instance;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IzinRequestRepositoy izinRequestRepositoy;

    @Before
    public void setUp() {
        instance = new IzinRequestServiceImpl(employeeRepository,izinRequestRepositoy);
    }


    @Test
    public void findAllRequest_Test() {
        Assert.assertNotNull(izinRequestRepositoy.findAll());
    }

    @Test
    public void requestIzin_AdvanceNumber_NotEnough_Test() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().name("name").startedDate(new Date()).surName("surname").build();
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        employee.setAdvanceIzinNumber(0L);
        employee.setDeservedIzinNumber(10L);
        employee = employeeRepository.save(employee);
        try {
            instance.requestIzin(employee.getId(), 2L);
        } catch (RestException e) {
            Assert.assertEquals(e.getMessage(), "employee.advance.number.not.enough");
        }
    }

    @Test
    public void requestIzin_AdvanceNumber_Enough_Test() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().name("name").startedDate(new Date()).surName("surname").build();
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        employee.setAdvanceIzinNumber(5L);
        employee.setDeservedIzinNumber(10L);
        employee = employeeRepository.save(employee);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setEmployeeId(employee.getId());
        requestDTO.setRequestedIzinNumber(2L);
        try {
            instance.requestIzin(requestDTO.getEmployeeId(), requestDTO.getRequestedIzinNumber());
        } catch (RestException e) {
            Assert.fail();
        }
    }

    @Test
    public void calculate_date_range() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 2, 31, 59, 59, 59);
        Date startedDate = calendar.getTime();
        int range = DateUtil.getDiffYears(startedDate, new Date());
        if (range != 3) {
            Assert.fail();
        }
    }
}
