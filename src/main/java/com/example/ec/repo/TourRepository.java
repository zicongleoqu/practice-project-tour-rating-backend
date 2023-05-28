package com.example.ec.repo;

import com.example.ec.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;


public interface TourRepository extends PagingAndSortingRepository<Tour, String> {
    Page<Tour> findByTourPackageCode(@Param("code")String code, Pageable pageable);

    //Mongo specific query to only return some fields
    @Query(value = "{'tourPackageCode' : ?0 }",
            fields = "{ 'id':1, 'title':1, 'tourPackageCode':1, 'tourPackageName':1}")
    Page<Tour> findSummaryByTourPackageCode(@Param("code")String code, Pageable pageable);

    boolean existsById(String tourId);

    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    <S extends Tour> Iterable<S> saveAll(Iterable<S> iterable);

    @RestResource(exported = false)
    void deleteById(String string);

    @RestResource(exported = false)
    void delete(Tour tour);


}
