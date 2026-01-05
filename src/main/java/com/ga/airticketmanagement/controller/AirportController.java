package com.ga.airticketmanagement.controller;

import com.ga.airticketmanagement.model.Airport;
import com.ga.airticketmanagement.repository.AirportRepository;
import com.ga.airticketmanagement.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AirportController {


    private AirportService airportService;

    @Autowired
    public void setAirportService(AirportService airportService){
        this.airportService = airportService;
    }

    @PostMapping("/airports")
    public Airport createAirport(@RequestBody Airport airport){

        return airportService.createAirport(airport);
    }

    @GetMapping("/airports/{id}")
    public Airport getAirport(@PathVariable Long id){

        return airportService.getAirport(id);
    }


    @GetMapping("/airports")
    public List<Airport> getAirports(){

        return airportService.getAirports();
    }

    @PutMapping("/airports/{id}")
    public Airport updateAirport(@PathVariable Long id, @RequestBody Airport airport){

        return airportService.updateAirport(id, airport);
    }

    @DeleteMapping("/airports/{id}")
    public String deleteAirport(@PathVariable Long id){

        return airportService.deleteAirport(id);
    }

}
