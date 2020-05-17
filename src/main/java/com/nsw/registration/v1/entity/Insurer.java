package com.nsw.registration.v1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Insurer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_details_id")
    private RegistrationDetails registrationDetails;
}
