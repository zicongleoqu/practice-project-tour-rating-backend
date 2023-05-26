package com.example.ec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class TourRatingPK implements Serializable {
    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false, nullable = false)
    private Integer customerId;

    public TourRatingPK() {
    }

    public TourRatingPK(Tour tour, Integer customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }
}
