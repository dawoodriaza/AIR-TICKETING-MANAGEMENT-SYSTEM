package com.ga.airticketmanagement.repository;

import com.ga.airticketmanagement.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionRef(String transactionRef);

    boolean existsByBookingId(Long bookingId);

    Page<Payment> findByUserId(Long userId, Pageable pageable);

    Page<Payment> findByBookingId(Long bookingId, Pageable pageable);

    Page<Payment> findByStatus(String status, Pageable pageable);
}