package com.nsw.registration.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class RegistrationDTO {

    private boolean expired;
    private OffsetDateTime expiry_date;
}
