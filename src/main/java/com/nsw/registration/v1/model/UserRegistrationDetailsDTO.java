package com.nsw.registration.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class UserRegistrationDetailsDTO {

    private Long userId;
    private List<RegistrationDetailsDTO> registrationDetails;

}
