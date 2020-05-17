package com.nsw.registration.v1.repository;

import com.nsw.registration.v1.entity.UserRegistrationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<UserRegistrationDetails, Long> {
}
