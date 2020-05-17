package com.nsw.registration.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDTO {

    private String type;
    private String make;
    private String model;
    private String colour;
    private String vin;
    private Integer tare_weight;
    private Integer gross_mass;

}
