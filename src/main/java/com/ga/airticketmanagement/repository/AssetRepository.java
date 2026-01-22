package com.ga.airticketmanagement.repository;



import com.ga.airticketmanagement.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public interface ImageRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByFileName(String fileName);


    boolean existsByFileName(String fileName);

    List<Asset> findByUserId(Long userId);




}