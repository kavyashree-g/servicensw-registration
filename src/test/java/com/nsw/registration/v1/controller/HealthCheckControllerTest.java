package com.nsw.registration.v1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HealthCheckControllerTest {

    @Autowired
    private HealthCheckController healthCheckController;

    @Test
    public void healthCheckTest(){
        assertEquals(healthCheckController.getHealthCheck(), new ResponseEntity(" Service NSW Registration APP Loaded Successfully ", HttpStatus.OK));
    }
}