package com.ga.airticketmanagement.dto.mapper;

import com.ga.airticketmanagement.dto.request.FlightRequest;
import com.ga.airticketmanagement.dto.response.FlightResponse;
import com.ga.airticketmanagement.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "originAirport.id", target = "originAirportId"),
            @Mapping(source = "destinationAirport.id", target = "destinationAirportId"),
    })
    FlightResponse toResponse(Flight flight);
    Flight toEntity(FlightRequest flightRequest);
}
