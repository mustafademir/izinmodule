package com.ykb.izinmodule.services;

import com.ykb.izinmodule.domain.entity.IzinRequest;

import java.util.List;

public interface IzinRequestService {
    void requestIzin(Long employeeId, Long requestedIzinNumber);
    void approveRequest(Long requestId);
    void denyRequest(Long requestId);
    List<IzinRequest> findAllRequests();
}
