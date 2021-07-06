package com.ykb.izinmodule.api;

import com.ykb.izinmodule.domain.dto.RequestDTO;
import com.ykb.izinmodule.services.IzinRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ykb.izinmodule.common.Constant.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = BASE + IZIN_REQUEST)
public class IzinRequestController {

    private final IzinRequestService izinRequestService;

    @PostMapping
    public ResponseEntity requestIzin(@RequestBody RequestDTO requestDTO) {
        izinRequestService.requestIzin(requestDTO.getEmployeeId(),requestDTO.getRequestedIzinNumber());
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "approve/{requestId}")
    public ResponseEntity approveRequest(@PathVariable Long requestId) {
        izinRequestService.approveRequest(requestId);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "deny/{requestId}")
    public ResponseEntity denyRequest(@PathVariable Long requestId) {
        izinRequestService.denyRequest(requestId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity findAllRequests() {
        return ResponseEntity.ok().body(izinRequestService.findAllRequests());
    }
}
