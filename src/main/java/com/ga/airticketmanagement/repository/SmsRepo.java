package com.ga.airticketmanagement.repository;

import com.ga.airticketmanagement.model.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepo extends JpaRepository<SmsLog, Long> {}

