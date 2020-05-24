package com.nsw.registration.v1.mapper;

import com.nsw.registration.v1.entity.RegistrationDetails;
import com.nsw.registration.v1.entity.UserRegistrationDetails;
import com.nsw.registration.v1.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseMapper {

    public UserRegistrationDetailsDTO userRegistrationDetailsResponseMapper( UserRegistrationDetails userRegistrationDetails){
        UserRegistrationDetailsDTO userRegistrationDetailsDTO = new UserRegistrationDetailsDTO();
        userRegistrationDetailsDTO.setUserId(userRegistrationDetails.getUserId());
        List<RegistrationDetailsDTO> registrationDetailsDTOList = new ArrayList<>();

        for (RegistrationDetails registrationDetails : userRegistrationDetails.getRegistrationDetails()) {
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
}
