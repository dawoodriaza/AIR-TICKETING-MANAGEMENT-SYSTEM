package com.ga.airticketmanagement.dto.mapper;

import com.ga.airticketmanagement.dto.request.AirportRequest;
import com.ga.airticketmanagement.dto.response.AirportResponse;
import com.ga.airticketmanagement.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public AirportResponse toResponse(Airport airport){
        return new AirportResponse(
                airport.getId(),
                airport.getName(),
                airport.getCountry(),
                airport.getCode()
        );
    }

    public Airport toEntity(AirportRequest request) {
        Airport airport = new Airport();
        airport.setName(request.getName());
        airport.setCountry(request.getCountry());
        airport.setCode(request.getCode());
        return airport;
    }
}
