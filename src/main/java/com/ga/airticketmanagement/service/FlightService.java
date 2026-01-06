package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.dto.request.CreateFlightByOriginAirportRequest;
import com.ga.airticketmanagement.dto.request.CreateFlightRequest;
import com.ga.airticketmanagement.dto.request.UpdateFlightByOriginAirportRequest;
import com.ga.airticketmanagement.dto.request.UpdateFlightRequest;
import com.ga.airticketmanagement.model.Airport;
import com.ga.airticketmanagement.model.Flight;
import com.ga.airticketmanagement.repository.AirportRepository;
import com.ga.airticketmanagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;


    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    public Flight createFlightByOriginAirport(Long originAirportId, CreateFlightByOriginAirportRequest flightObject) {
        System.out.println(flightObject);
        Airport origin = airportRepository.findById(originAirportId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));
        Airport destination = airportRepository.findById(flightObject.getDestinationAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination Airport not found"));

        if(origin.getId().equals(destination.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Destination Airport and Destination Airport are the same");
        }

        Flight flight = new Flight();
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setPrice(flightObject.getPrice());
        flight.setDepartureTime(flightObject.getDepartureTime());
        flight.setArrivalTime(flightObject.getArrivalTime());

        return flightRepository.save(flight);
    }

    public Flight createFlight(CreateFlightRequest flightObject) {

        Airport origin = airportRepository.findById(flightObject.getOriginAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));
        Airport destination = airportRepository.findById(flightObject.getDestinationAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination Airport not found"));

        if(origin.getId().equals(destination.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Destination Airport and Destination Airport are the same");
        }

        Flight flight = new Flight();
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setPrice(flightObject.getPrice());
        flight.setDepartureTime(flightObject.getDepartureTime());
        flight.setArrivalTime(flightObject.getArrivalTime());

        return flightRepository.save(flight);
    }

    public Flight updateFlightByOriginAirport(Long airportId, Long flightId, UpdateFlightByOriginAirportRequest flightObject) {

        Flight flight = validateFlight(airportId, flightId);
        Airport destinationAirport = airportRepository.findById(flightObject.getDestinationAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination Airport not found"));

        flight.setDestinationAirport(destinationAirport);
        flight.setDepartureTime(flightObject.getDepartureTime());
        flight.setArrivalTime(flightObject.getArrivalTime());
        flight.setPrice(flightObject.getPrice());

        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long flightId,UpdateFlightRequest flightObject) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
        Airport originAirport = airportRepository.findById(flightObject.getOriginAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination Airport not found"));
        Airport destinationAirport = airportRepository.findById(flightObject.getDestinationAirportId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination Airport not found"));

        flight.setOriginAirport(originAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setDepartureTime(flightObject.getDepartureTime());
        flight.setArrivalTime(flightObject.getArrivalTime());
        flight.setPrice(flightObject.getPrice());

        return flightRepository.save(flight);
    }

    public String deleteFlight(Long flightId) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));

        flightRepository.delete(flight);

        return "Flight deleted successfully";
    }

    public Flight getFlight(Long airportId, Long flightId) {

        Flight flight = validateFlight(airportId, flightId);

        return flight;
    }

    public List<Flight> getFlights() {

        return flightRepository.findAll();

    }

    public List<Flight> getAirportFlights(Long airportId) {

        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));

        return flightRepository.findByAirport(airport.getId());

    }

    public List<Flight> getAirportDepartures(Long airportId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));

        return flightRepository.findByOriginAirport(airport.getId());
    }

    public List<Flight> getAirportArrivals(Long airportId) {
        Airport airport = airportRepository.findById(airportId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));

        return flightRepository.findByDestinationAirport(airport.getId());
    }

    private Flight validateFlight(Long airportId, Long flightId) {
        airportRepository.findById(airportId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport " + airportId + " not found"));

        Flight flight = flightRepository.findByIdAndAirport(airportId, flightId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight " + flightId + " not found")
        );

        return flight;
    }


}
