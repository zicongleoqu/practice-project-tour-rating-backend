package com.example.ec.repo;

import com.example.ec.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends CrudRepository<Tour, Integer> {
    Optional<Tour> findByTourPackageCode(@Param("code")String code);
}
