package com.nsw.registration.v1.mapper;

import com.nsw.registration.v1.entity.*;
import com.nsw.registration.v1.model.UserRegistrationDetailsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ResponseMapperTest {

    @Autowired
    private ResponseMapper responseMapper;

    @Test
    public void userRegistrationDetailsResponseMapperTest(){
        UserRegistrationDetailsDTO userRegistrationDetailsDTO = responseMapper.userRegistrationDetailsResponseMapper(generateUserRegistrationDetails());
        Assertions.assertEquals(userRegistrationDetailsDTO.getUserId(),10001L);
        Assertions.assertEquals(userRegistrationDetailsDTO.getRegistrationDetails().get(0).getPlate_number(), "EBF28E");
        Assertions.assertEquals(userRegistrationDetailsDTO.getRegistrationDetails().get(0).getVehicle().getColour(), "green");
        Assertions.assertEquals(userRegistrationDetailsDTO.getRegistrationDetails().get(0).getInsurer().getName(), "Allianz");
        Assertions.assertEquals(userRegistrationDetailsDTO.getRegistrationDetails().get(0).getRegistration().isExpired(), false);

    }

    public UserRegistrationDetails generateUserRegistrationDetails(){
        UserRegistrationDetails userRegistrationDetails =new UserRegistrationDetails();
        userRegistrationDetails.setUserId(10001L);
        List<RegistrationDetails> registrationDetailsList = new ArrayList<>();
        RegistrationDetails registrationDetails = new RegistrationDetails();
        registrationDetails.setPlate_number("EBF28E");

        Insurer insurer = new Insurer();
        insurer.setCode(32);
        insurer.setName("Allianz");
        insurer.setRegistrationDetails(registrationDetails);
        registrationDetails.setInsurer(insurer);

        Vehicle vehicle = new Vehicle();
        vehicle.setColour("green");
        vehicle.setMake("BMW");
        vehicle.setModel("X4 M40i");
        vehicle.setVin("12389347324");
        vehicle.setType("Wagon");
        vehicle.setTare_weight(1700);
        vehicle.setGross_mass(1300);
        vehicle.setRegistrationDetails(registrationDetails);
        registrationDetails.setVehicle(vehicle);

        Registration registration= new Registration();
        registration.setExpired(false);
        registration.setExpiry_date(OffsetDateTime.parse("2021-02-05T23:15:30.000Z"));
        registration.setRegistrationDetails(registrationDetails);
        registrationDetails.setRegistration(registration);
        registrationDetails.setUserRegistrationDetails(userRegistrationDetails);

        registrationDetailsList.add(registrationDetails);
        userRegistrationDetails.setRegistrationDetails(registrationDetailsList);
        return userRegistrationDetails;
    }
}
