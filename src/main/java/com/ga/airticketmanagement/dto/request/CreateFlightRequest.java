package com.ga.airticketmanagement.dto.request;

import com.ga.airticketmanagement.repository.AirportRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CreateFlightRequest extends CreateFlightByOriginAirportRequest {

    private Long originAirportId;

}
