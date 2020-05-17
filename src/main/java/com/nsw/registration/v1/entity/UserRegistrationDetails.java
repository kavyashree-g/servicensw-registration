package com.nsw.registration.v1.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class UserRegistrationDetails {
    @Id
    @GeneratedValue
    private Long userId;

    @OneToMany(mappedBy = "userRegistrationDetails", cascade = CascadeType.ALL)
    private List<RegistrationDetails> registrationDetails;
}


