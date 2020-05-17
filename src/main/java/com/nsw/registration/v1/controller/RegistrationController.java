package com.nsw.registration.v1.controller;

import com.nsw.registration.v1.exception.ResourceNotFoundException;
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
    public ResponseEntity<UserRegistrationDetailsDTO> getRegistrationsByUserId(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
        UserRegistrationDetailsDTO response = null;
        log.info(" Get API Invoked for userId : ", userId);
        try{
            response = registrationService.getRegistrationsByUserId(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createUserRegistrationDetails(@RequestBody UserRegistrationDetailsDTO userRegistrationDetails) {
        log.info(" Create UserRegistrationDetails : ", userRegistrationDetails);
        Long userIdCreated = registrationService.createUserRegistrationDetails(userRegistrationDetails);
        return new ResponseEntity(userIdCreated, HttpStatus.CREATED);
    }
}
