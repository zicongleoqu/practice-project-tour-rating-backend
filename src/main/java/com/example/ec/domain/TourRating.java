package com.example.ec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TourRating {

    @EmbeddedId
    private TourRatingPK pK;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;



}
