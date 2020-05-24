package com.nsw.registration.v1.mapper;

import com.nsw.registration.v1.entity.*;
import com.nsw.registration.v1.model.RegistrationDetailsDTO;
import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestMapper {

    public UserRegistrationDetails userRegistrationDetailsRequestMapper(UserRegistrationDetailsDTO userRegistrationDetailsDTO) {
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
        return userRegistrationDetails;
    }
}
