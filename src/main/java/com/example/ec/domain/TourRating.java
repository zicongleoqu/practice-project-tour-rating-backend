package com.example.ec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TourRating {

    @EmbeddedId
    private TourRatingPK PK;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    public TourRating(TourRatingPK PK, Integer score, String comment) {
        this.PK = PK;
        this.score = score;
        this.comment = comment;
    }

    public TourRating() {}

    public TourRatingPK getpK() {
        return PK;
    }

    public void setpK(TourRatingPK pK) {
        this.PK = pK;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
