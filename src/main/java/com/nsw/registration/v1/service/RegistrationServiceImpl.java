package com.nsw.registration.v1.service;

import com.nsw.registration.v1.entity.*;
import com.nsw.registration.v1.model.*;
import com.nsw.registration.v1.repository.RegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public UserRegistrationDetailsDTO getRegistrationsByUserId(Long userId) {
        UserRegistrationDetails userRegistrationDetails = registrationRepository.getOne(userId);
        log.info("User Registration Details : ", userRegistrationDetails);

        UserRegistrationDetailsDTO userRegistrationDetailsDTO = new UserRegistrationDetailsDTO();
        userRegistrationDetailsDTO.setUserId(userRegistrationDetails.getUserId());
        List<RegistrationDetailsDTO> registrationDetailsDTOList = new ArrayList<>();

        for (RegistrationDetails registrationDetails :userRegistrationDetails.getRegistrationDetails()){
            RegistrationDetailsDTO registrationDetailsDTO = new RegistrationDetailsDTO();
            registrationDetailsDTO.setPlate_number(registrationDetails.getPlate_number());

            InsurerDTO insurerDTO = new InsurerDTO();
            insurerDTO.setCode(registrationDetails.getInsurer().getCode());
            insurerDTO.setName(registrationDetails.getInsurer().getName());
            registrationDetailsDTO.setInsurer(insurerDTO);

            RegistrationDTO registrationDTO = new RegistrationDTO();
            registrationDTO.setExpired(registrationDetails.getRegistration().isExpired());
            registrationDTO.setExpiry_date(registrationDetails.getRegistration().getExpiry_date());
            registrationDetailsDTO.setRegistration(registrationDTO);

            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setColour(registrationDetails.getVehicle().getColour());
            vehicleDTO.setMake(registrationDetails.getVehicle().getMake());
            vehicleDTO.setModel(registrationDetails.getVehicle().getModel());
            vehicleDTO.setGross_mass(registrationDetails.getVehicle().getGross_mass());
            vehicleDTO.setTare_weight(registrationDetails.getVehicle().getTare_weight());
            vehicleDTO.setType(registrationDetails.getVehicle().getType());
            vehicleDTO.setVin(registrationDetails.getVehicle().getVin());
            registrationDetailsDTO.setVehicle(vehicleDTO);

            registrationDetailsDTOList.add(registrationDetailsDTO);
        }
        userRegistrationDetailsDTO.setRegistrationDetails(registrationDetailsDTOList);
        return userRegistrationDetailsDTO;
    }

    @Override
    public Long createUserRegistrationDetails(UserRegistrationDetailsDTO userRegistrationDetailsDTO) {
        UserRegistrationDetails userRegistrationDetails = new UserRegistrationDetails();
        List<RegistrationDetails> registrationDetailsList = new ArrayList<>();
        for (RegistrationDetailsDTO dto : userRegistrationDetailsDTO.getRegistrationDetails()) {
            RegistrationDetails registrationDetails = new RegistrationDetails();
            registrationDetails.setPlate_number(dto.getPlate_number());

            Insurer insurer = new Insurer();
            insurer.setCode(dto.getInsurer().getCode());
            insurer.setName(dto.getInsurer().getName());
            insurer.setRegistrationDetails(registrationDetails);
            registrationDetails.setInsurer(insurer);

            Vehicle vehicle = new Vehicle();
            vehicle.setColour(dto.getVehicle().getColour());
            vehicle.setGross_mass(dto.getVehicle().getGross_mass());
            vehicle.setMake(dto.getVehicle().getMake());
            vehicle.setModel(dto.getVehicle().getModel());
            vehicle.setType(dto.getVehicle().getType());
            vehicle.setVin(dto.getVehicle().getVin());
            vehicle.setTare_weight(dto.getVehicle().getTare_weight());
            vehicle.setRegistrationDetails(registrationDetails);
            registrationDetails.setVehicle(vehicle);

            Registration registration = new Registration();
            registration.setExpired(dto.getRegistration().isExpired());
            registration.setExpiry_date(dto.getRegistration().getExpiry_date());
            registration.setRegistrationDetails(registrationDetails);
            registrationDetails.setRegistration(registration);
            registrationDetails.setUserRegistrationDetails(userRegistrationDetails);

            registrationDetailsList.add(registrationDetails);
        }
        userRegistrationDetails.setRegistrationDetails(registrationDetailsList);
        UserRegistrationDetails userRegistrationDetailsSaved = registrationRepository.save(userRegistrationDetails);
        log.info("UserRegistrationDetails Created : ", userRegistrationDetailsSaved);
        return userRegistrationDetailsSaved.getUserId();
    }
}
