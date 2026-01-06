package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.request.CreateFlightRequest;
import com.ga.airticketmanagement.dto.request.UpdateFlightRequest;
import com.ga.airticketmanagement.model.Flight;
import com.ga.airticketmanagement.repository.FlightRepository;
import com.ga.airticketmanagement.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {

        return flightService.getFlights();
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody CreateFlightRequest flightObject) {

        Flight flight = flightService.createFlight(flightObject);

        return ResponseEntity.status(HttpStatus.CREATED).body(flight);
    }

    @PutMapping("/flights/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody UpdateFlightRequest flightObject) {

        Flight flight = flightService.updateFlight(flightId, flightObject);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @DeleteMapping("/flights/{flightId}")
    public String deleteFlight(@PathVariable Long flightId) {

        return flightService.deleteFlight(flightId);
    }

}
