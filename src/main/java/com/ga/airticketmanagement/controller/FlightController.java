package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.request.FlightRequest;
import com.ga.airticketmanagement.dto.response.FlightResponse;
import com.ga.airticketmanagement.dto.response.ListResponse;
import com.ga.airticketmanagement.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ListResponse<FlightResponse> getFlights(Pageable pageable) {

        return flightService.getFlights(pageable);
    }

    @GetMapping("/flights/{flightId}")
    public FlightResponse getFlight(@PathVariable Long flightId) {

        return flightService.getFlight(flightId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/flights")
    public FlightResponse createFlight(@RequestBody FlightRequest flightObject) {

        return flightService.createFlight(flightObject);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/flights/{flightId}")
    public FlightResponse updateFlight(@PathVariable Long flightId, @RequestBody FlightRequest flightObject) {

        return flightService.updateFlight(flightId, flightObject);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/flights/{flightId}")
    public void deleteFlight(@PathVariable Long flightId) {

        flightService.deleteFlight(flightId);
    }
}
