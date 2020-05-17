package com.nsw.registration.v1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private String vin;

    @Column(name = "TARE_WEIGHT")
    private Integer tare_weight;

    @Column(name = "GROSS_MASS")
    private Integer gross_mass;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="registration_details_id")
    private RegistrationDetails registrationDetails;
}
