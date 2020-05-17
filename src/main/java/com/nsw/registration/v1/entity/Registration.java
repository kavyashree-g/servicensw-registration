package com.nsw.registration.v1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Registration {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private boolean expired;

    @Column(name = "EXPIRY_DATE")
    private OffsetDateTime expiry_date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_details_id")
    private RegistrationDetails registrationDetails;


}
