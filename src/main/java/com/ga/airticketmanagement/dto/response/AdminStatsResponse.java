package com.ga.airticketmanagement.dto.response;

public record AdminStatsResponse(
        Long totalBookings,
        Long totalFlights,
        Long totalUsers,
        Double totalRevenue
) {}
