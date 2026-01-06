package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.model.Airport;
import com.ga.airticketmanagement.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }

    public Airport createAirport(Airport airportObject){

        airportRepository.findByName(airportObject.getName()).ifPresent(
                a -> new ResponseStatusException(HttpStatus.CONFLICT, "Airport " + a.getName() + " already exists.")
        );

        return airportRepository.save(airportObject);
    }

    public Airport getAirport(Long id){

        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport " + id + " does not exist.")
        );

        return  airport;
    }


    public List<Airport> getAirports(){

        List<Airport> airports = airportRepository.findAll();

        return airports;
    }

    public Airport updateAirport(Long id, Airport airportObject){

        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport " + id + " does not exist.")
        );

        Optional<Airport> exists = airportRepository.findByName(airportObject.getName());

        if(exists.isPresent() && exists.get().getName().equals(airportObject.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Airport " + airportObject.getName() + " already exists.");
        }

        airport.setName(airportObject.getName());
        airport.setCountry(airportObject.getCountry());
        airport.setCode(airportObject.getCode());

        return airportRepository.save(airport);
    }

    public String deleteAirport(Long id){

        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport " + id + " does not exist.")
        );

        airportRepository.delete(airport);

        return "Airport " + id + " was deleted.";
    }
}
