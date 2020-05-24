package com.nsw.registration.v1.mapper;

import com.nsw.registration.v1.entity.UserRegistrationDetails;
import com.nsw.registration.v1.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RequestMapperTest {

    @Autowired
    private RequestMapper requestMapper;

    @Test
    public void userRegistrationDetailsRequestMapperTest(){
        UserRegistrationDetails userRegistrationDetails =  requestMapper.userRegistrationDetailsRequestMapper(generateUserRegistrationDetailsDTO());
        Assertions.assertEquals(userRegistrationDetails.getRegistrationDetails().get(0).getPlate_number(),"EBF28E");
        Assertions.assertEquals(userRegistrationDetails.getRegistrationDetails().get(0).getVehicle().getColour(),"green");
        Assertions.assertEquals(userRegistrationDetails.getRegistrationDetails().get(0).getInsurer().getName(),"Allianz");
        Assertions.assertEquals(userRegistrationDetails.getRegistrationDetails().get(0).getRegistration().isExpired(),false);
    }
    public UserRegistrationDetailsDTO generateUserRegistrationDetailsDTO(){
        UserRegistrationDetailsDTO userRegistrationDetailsDTO =new UserRegistrationDetailsDTO();
        userRegistrationDetailsDTO.setUserId(10001L);
        List<RegistrationDetailsDTO> registrationDetailsList = new ArrayList<>();
        RegistrationDetailsDTO registrationDetailsDTO = new RegistrationDetailsDTO();
        registrationDetailsDTO.setPlate_number("EBF28E");

        InsurerDTO insurer = new InsurerDTO();
        insurer.setCode(32);
        insurer.setName("Allianz");
        registrationDetailsDTO.setInsurer(insurer);

        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setColour("green");
        vehicle.setMake("BMW");
        vehicle.setModel("X4 M40i");
        vehicle.setVin("12389347324");
        vehicle.setType("Wagon");
        vehicle.setTare_weight(1700);
        vehicle.setGross_mass(1300);
        registrationDetailsDTO.setVehicle(vehicle);

        RegistrationDTO registration= new RegistrationDTO();
        registration.setExpired(false);
        registration.setExpiry_date(OffsetDateTime.parse("2021-02-05T23:15:30.000Z"));
        registrationDetailsDTO.setRegistration(registration);

        registrationDetailsList.add(registrationDetailsDTO);
        userRegistrationDetailsDTO.setRegistrationDetails(registrationDetailsList);
        return userRegistrationDetailsDTO;
    }
}
