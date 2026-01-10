package com.ga.airticketmanagement.dto.response.airport;

public record CreateAirportResponse (
        Long id,
        String name,
        String country,
        String code
){}
