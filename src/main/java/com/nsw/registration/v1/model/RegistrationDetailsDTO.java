package com.nsw.registration.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDetailsDTO {

    private String plate_number;
    private RegistrationDTO registration;
    private VehicleDTO vehicle;
    private InsurerDTO insurer;

}