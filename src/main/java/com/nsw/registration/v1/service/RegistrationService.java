package com.nsw.registration.v1.service;

import com.nsw.registration.v1.exception.ResourceNotFoundException;
import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;

public interface RegistrationService {

    public UserRegistrationDetailsDTO getRegistrationsByUserId(Long userId) throws ResourceNotFoundException;

    public Long createUserRegistrationDetails(UserRegistrationDetailsDTO userRegistrationDetails);
}
