package com.idigital.epam.energy.payload;

import com.idigital.epam.energy.enums.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyResponse {
    private Long id;
    //private Integer previousReading;
    private Integer energyConsumption;
    private Long homeCode;
    private BuildingType buildingType;
    private  String cardNumber;
    private String firstName;

}
