package com.nsw.registration.v1.controller;

import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import com.nsw.registration.v1.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationServiceMock;

    @Mock
    private UserRegistrationDetailsDTO userRegistrationDetailsDTOMock;

    @InjectMocks
    private RegistrationController registrationControllerMock;

    @Test
    public void getRegistrationsByUserIdTest() {
        Mockito.when(registrationServiceMock.getRegistrationsByUserId(Mockito.anyLong())).thenReturn(userRegistrationDetailsDTOMock);
        ResponseEntity<UserRegistrationDetailsDTO> response = registrationControllerMock.getRegistrationsByUserId(Mockito.anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getRegistrationsByUserIdExceptionTest() {
        Mockito.when(registrationServiceMock.getRegistrationsByUserId(Mockito.anyLong())).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(HttpServerErrorException.class, () -> registrationControllerMock.getRegistrationsByUserId(Mockito.anyLong()));
    }

    @Test
    public void createUserRegistrationDetailsTest() {
        Mockito.when(registrationServiceMock.createUserRegistrationDetails(userRegistrationDetailsDTOMock)).thenReturn(new Long(10001L));
        ResponseEntity<Long> response = registrationControllerMock.createUserRegistrationDetails(userRegistrationDetailsDTOMock);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void createUserRegistrationDetailsExceptionTest() {
        Mockito.when(registrationServiceMock.createUserRegistrationDetails(userRegistrationDetailsDTOMock)).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(HttpServerErrorException.class, () -> registrationControllerMock.createUserRegistrationDetails(userRegistrationDetailsDTOMock));
    }


}