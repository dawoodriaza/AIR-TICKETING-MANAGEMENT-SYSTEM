package com.ga.airticketmanagement.repository;

import com.ga.airticketmanagement.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {}
