package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.response.AdminStatsResponse;
import com.ga.airticketmanagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats")
    public AdminStatsResponse getStats() {
        return adminService.getStats();
    }
}
