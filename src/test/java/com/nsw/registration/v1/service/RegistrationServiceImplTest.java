package com.nsw.registration.v1.service;

import com.nsw.registration.v1.entity.*;
import com.nsw.registration.v1.model.*;
import com.nsw.registration.v1.repository.RegistrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RegistrationServiceImplTest {
    @Mock
    private RegistrationRepository registrationRepositoryMock;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    public void getRegistrationsByUserIdTest(){

        UserRegistrationDetails userRegistrationDetails = generateUserRegistrationDetails();
        Mockito.when(registrationRepositoryMock.getOne(Mockito.anyLong())).thenReturn(userRegistrationDetails);
        UserRegistrationDetailsDTO userRegistrationDetailsDTO = registrationService.getRegistrationsByUserId(Mockito.anyLong());
        Assertions.assertEquals("EBF28E",userRegistrationDetailsDTO.getRegistrationDetails().get(0).getPlate_number());
        Assertions.assertEquals("green",userRegistrationDetailsDTO.getRegistrationDetails().get(0).getVehicle().getColour());
        Assertions.assertEquals("Allianz",userRegistrationDetailsDTO.getRegistrationDetails().get(0).getInsurer().getName());
        Assertions.assertEquals(false,userRegistrationDetailsDTO.getRegistrationDetails().get(0).getRegistration().isExpired());
    }

    @Test
    public void createUserRegistrationDetailsTest(){
        UserRegistrationDetails userRegistrationDetails = generateUserRegistrationDetails();
        Mockito.when(registrationRepositoryMock.save(Mockito.any())).thenReturn(userRegistrationDetails);
        UserRegistrationDetailsDTO userRegistrationDetailsDTO = generateUserRegistrationDetailsDTO();
        Long userId = registrationService.createUserRegistrationDetails(userRegistrationDetailsDTO);
        Assertions.assertEquals(10001L, userId);
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
    public UserRegistrationDetailsDTO generateUserRegistrationDetailsDTO(){
        UserRegistrationDetailsDTO userRegistrationDetailsDTO =new UserRegistrationDetailsDTO();
        //userRegistrationDetailsDTO.setUserId(10001L);
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
