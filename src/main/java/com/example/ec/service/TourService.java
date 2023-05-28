package com.example.ec.service;

import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import com.example.ec.repo.TourRepository;
import com.example.ec.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String tourPackageName,
                           Map<String, String> details) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageName));

        return tourRepository.save(new Tour(title, tourPackage, details));
    }

}
