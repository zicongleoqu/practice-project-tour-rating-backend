package com.example.ec.domain;

import jakarta.persistence.*;

@Entity
public class TourPackage {
    @Id
    private String code;

    @Column
    private String name;

    protected TourPackage() {

    }

    public TourPackage(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
