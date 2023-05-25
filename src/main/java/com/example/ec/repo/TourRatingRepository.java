package com.example.ec.repo;

import com.example.ec.domain.TourRating;
import com.example.ec.domain.TourRatingPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPK> {
    List<TourRating> findByPKTourId(Integer tourId);

    Optional<TourRating> findByPKTourIdAndPKCustomerId(Integer tourId, Integer customer);
}



