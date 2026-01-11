package com.ga.airticketmanagement.dto.response;

public record AirportResponse (
        Long id,
        String name,
        String country,
        String code
){}
