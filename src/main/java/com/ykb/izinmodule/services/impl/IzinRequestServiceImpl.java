package com.ykb.izinmodule.services.impl;

import com.ykb.izinmodule.domain.entity.Employee;
import com.ykb.izinmodule.domain.entity.IzinRequest;
import com.ykb.izinmodule.repositories.EmployeeRepository;
import com.ykb.izinmodule.repositories.IzinRequestRepositoy;
import com.ykb.izinmodule.services.IzinRequestService;
import com.ykb.izinmodule.util.DateUtil;
import com.ykb.izinmodule.common.http.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ykb.izinmodule.enums.IzinRequestStatus.*;

@Service
@RequiredArgsConstructor
public class IzinRequestServiceImpl implements IzinRequestService {

    private final EmployeeRepository employeeRepository;
    private final IzinRequestRepositoy izinRequestRepositoy;

    @Override
    public void requestIzin(Long employeeId, Long requestedIzinNumber) {
        IzinRequest izinRequest = new IzinRequest();
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (!optionalEmployee.isPresent()) {
            throw new RestException("employee.not.found", null);
        }
        Employee employee = optionalEmployee.get();
        int passedYear = DateUtil.getDiffYears(employee.getStartedDate(), new Date());
        if (passedYear < 1) {
            if (employee.getAdvanceIzinNumber() < requestedIzinNumber) {
                throw new RestException("employee.advance.number.not.enough", null);
            }
        } else {
            if (employee.getDeservedIzinNumber() < requestedIzinNumber) {
                throw new RestException("employee.deserved.number.not.enough", null);
            }
        }
        izinRequest.setEmployee(employee);
        izinRequest.setRequestedIzinNumber(requestedIzinNumber);
        izinRequest.setStatus(CONFIRMATION_WAITING);
        izinRequestRepositoy.save(izinRequest);
    }

    @Override
    @Transactional
    public void approveRequest(Long requestId) {
        Optional<IzinRequest> optionalIzinRequest = izinRequestRepositoy.findById(requestId);
        if (!optionalIzinRequest.isPresent()) {
            throw new RestException("request.not.found", null);
        }
        IzinRequest izinRequest = optionalIzinRequest.get();
        Employee employee = izinRequest.getEmployee();
        if (DateUtil.getDiffYears(employee.getStartedDate(), new Date()) < 1) {
            employee.setAdvanceIzinNumber(employee.getAdvanceIzinNumber() - izinRequest.getRequestedIzinNumber());
        } else {
            employee.setDeservedIzinNumber(employee.getDeservedIzinNumber() - izinRequest.getRequestedIzinNumber());
        }
        izinRequest.setEmployee(employee);
        izinRequest.setStatus(APPROVED);
        izinRequestRepositoy.save(izinRequest);
    }

    @Override
    @Transactional
    public void denyRequest(Long requestId) {
        Optional<IzinRequest> optionalIzinRequest = izinRequestRepositoy.findById(requestId);
        if (!optionalIzinRequest.isPresent()) {
            throw new RestException("request.not.found", null);
        }
        IzinRequest izinRequest = optionalIzinRequest.get();
        izinRequest.setStatus(DENIED);
        izinRequestRepositoy.save(izinRequest);
    }

    @Override
    public List<IzinRequest> findAllRequests() {
        return izinRequestRepositoy.findAll();
    }
}
