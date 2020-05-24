package com.nsw.registration.v1.service;

import com.nsw.registration.v1.entity.UserRegistrationDetails;
import com.nsw.registration.v1.exception.ResourceNotFoundException;
import com.nsw.registration.v1.mapper.RequestMapper;
import com.nsw.registration.v1.mapper.ResponseMapper;
import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import com.nsw.registration.v1.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ResponseMapper responseMapper;

    public UserRegistrationDetailsDTO getRegistrationsByUserId(Long userId) throws ResourceNotFoundException {
        UserRegistrationDetails userRegistrationDetails = null;
        try {
            userRegistrationDetails = registrationRepository.getOne(userId);
        } catch (HttpServerErrorException | HttpClientErrorException | EntityNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        UserRegistrationDetailsDTO userRegistrationDetailsDTO = responseMapper.userRegistrationDetailsResponseMapper(userRegistrationDetails);
        return userRegistrationDetailsDTO;
    }

    @Override
    public Long createUserRegistrationDetails(UserRegistrationDetailsDTO userRegistrationDetailsDTO) {
        UserRegistrationDetails userRegistrationDetails = requestMapper.userRegistrationDetailsRequestMapper(userRegistrationDetailsDTO);
        UserRegistrationDetails userRegistrationDetailsSaved = registrationRepository.save(userRegistrationDetails);
        log.info("UserRegistrationDetails Created : ", userRegistrationDetailsSaved);
        return userRegistrationDetailsSaved.getUserId();
    }
}
