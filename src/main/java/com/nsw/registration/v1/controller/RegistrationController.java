package com.nsw.registration.v1.controller;

import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import com.nsw.registration.v1.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registrations")
@Slf4j
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping({"/{userId}"})
    public ResponseEntity<UserRegistrationDetailsDTO> getRegistrationsByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(registrationService.getRegistrationsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createUserRegistrationDetails(@RequestBody UserRegistrationDetailsDTO userRegistrationDetails) {
        log.info(" User Input : {}", userRegistrationDetails);
        Long userIdCreated = registrationService.createUserRegistrationDetails(userRegistrationDetails);
        return new ResponseEntity(userIdCreated, HttpStatus.CREATED);
    }
}
