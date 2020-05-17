package com.nsw.registration.v1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String plate_number;

    @OneToOne(mappedBy = "registrationDetails", cascade = CascadeType.ALL)
    private Registration registration;

    @OneToOne(mappedBy = "registrationDetails", cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @OneToOne(mappedBy = "registrationDetails", cascade = CascadeType.ALL)
    private Insurer insurer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserRegistrationDetails userRegistrationDetails;

}