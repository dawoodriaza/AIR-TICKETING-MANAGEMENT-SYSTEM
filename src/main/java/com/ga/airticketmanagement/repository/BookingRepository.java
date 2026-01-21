package com.ga.airticketmanagement.repository;

import com.ga.airticketmanagement.model.Booking;
import com.ga.airticketmanagement.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    
    @Query("SELECT COALESCE(SUM(b.flight.price), 0) FROM Booking b WHERE b.status = :status")
    BigDecimal sumFlightPriceByStatus(@Param("status") BookingStatus status);
}
