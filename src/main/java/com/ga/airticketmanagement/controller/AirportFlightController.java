package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.dto.request.CreateFlightRequest;
import com.ga.airticketmanagement.dto.request.UpdateFlightByOriginAirportRequest;
import com.ga.airticketmanagement.model.Flight;
import com.ga.airticketmanagement.service.AirportService;
import com.ga.airticketmanagement.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/airports")
public class AirportFlightController {

    private FlightService flightService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/{originAirportId}/flights")
    public ResponseEntity<Flight> createFlightByOriginAirport(@PathVariable Long originAirportId, @RequestBody CreateFlightRequest flightObject) {

        Flight flight = flightService.createFlightByOriginAirport(originAirportId, flightObject);

        return ResponseEntity.status(HttpStatus.CREATED).body(flight);
    }

    @PutMapping("/{airportId}/flights/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long airportId, @PathVariable Long flightId, @RequestBody UpdateFlightByOriginAirportRequest flightObject) {

        Flight flight = flightService.updateFlightByOriginAirport(airportId, flightId, flightObject);
        return ResponseEntity.status(HttpStatus.OK).body(flight);
    }

    @GetMapping("/{airportId}/flights/{flightId}")
    public Flight getFlight(@PathVariable Long airportId, @PathVariable Long flightId) {

        return flightService.getFlight(airportId, flightId);
    }

    @GetMapping("/{airportId}/flights")
    public List<Flight> getAirportFlights(@PathVariable Long airportId) {

        return flightService.getAirportFlights(airportId);
    }


    @GetMapping("/{airportId}/departures")
    public List<Flight> getAirportDepartures(@PathVariable Long airportId) {

        return flightService.getAirportDepartures(airportId);
    }

    @GetMapping("/{airportId}/arrivals")
    public List<Flight> getAirportArrivals(@PathVariable Long airportId) {

        return flightService.getAirportArrivals(airportId);
    }



}
