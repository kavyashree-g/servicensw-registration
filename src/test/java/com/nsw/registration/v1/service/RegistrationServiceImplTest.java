package com.nsw.registration.v1.service;

import com.nsw.registration.v1.entity.UserRegistrationDetails;
import com.nsw.registration.v1.exception.ResourceNotFoundException;
import com.nsw.registration.v1.mapper.RequestMapper;
import com.nsw.registration.v1.mapper.ResponseMapper;
import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import com.nsw.registration.v1.repository.RegistrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@SpringBootTest
public class RegistrationServiceImplTest {
    @Mock
    private RegistrationRepository registrationRepositoryMock;

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private ResponseMapper responseMapper;

    @Mock
    private UserRegistrationDetails userRegistrationDetails;

    @Mock
    private UserRegistrationDetailsDTO userRegistrationDetailsDTO;

    @InjectMocks
    private RegistrationServiceImpl registrationServiceImpl;

    @Test
    public void getRegistrationsByUserIdTest() throws ResourceNotFoundException {
        Mockito.when(registrationRepositoryMock.getOne(Mockito.anyLong())).thenReturn(userRegistrationDetails);
        Mockito.when(responseMapper.userRegistrationDetailsResponseMapper(Mockito.any())).thenReturn(userRegistrationDetailsDTO);
        UserRegistrationDetailsDTO responseDTO = registrationServiceImpl.getRegistrationsByUserId(Mockito.anyLong());
        Assertions.assertEquals(responseDTO, userRegistrationDetailsDTO);
    }

    @Test
    public void createUserRegistrationDetailsTest() {
        Mockito.when(requestMapper.userRegistrationDetailsRequestMapper(Mockito.any())).thenReturn(userRegistrationDetails);
        Mockito.when(registrationRepositoryMock.save(Mockito.any())).thenReturn(userRegistrationDetails);
        Long userId = registrationServiceImpl.createUserRegistrationDetails(userRegistrationDetailsDTO);
        Assertions.assertEquals(userId, userRegistrationDetails.getUserId());
    }

    public void getRegistrationsByUserIdExceptionTest() throws ResourceNotFoundException {
        Assertions.assertThrows(ResourceNotFoundException.class, (Executable) registrationServiceImpl.getRegistrationsByUserId(123456L));
    }

    public void getRegistrationsByUserIdHttpServerExceptionTest() throws ResourceNotFoundException {
        Mockito.when(registrationRepositoryMock.getOne(Mockito.anyLong())).thenThrow(HttpServerErrorException.class);
        Assertions.assertThrows(HttpServerErrorException.class, (Executable) registrationServiceImpl.getRegistrationsByUserId(123456L));
    }

    public void getRegistrationsByUserIdHttpClientExceptionTest() throws ResourceNotFoundException {
        Mockito.when(registrationRepositoryMock.getOne(Mockito.anyLong())).thenThrow(HttpClientErrorException.class);
        Assertions.assertThrows(HttpClientErrorException.class, (Executable) registrationServiceImpl.getRegistrationsByUserId(123456L));
    }
}
