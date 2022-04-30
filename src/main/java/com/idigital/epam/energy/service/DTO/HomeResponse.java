package com.idigital.epam.energy.service.DTO;

import com.idigital.epam.energy.enums.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeResponse {
    private Long id;
    private String homeNumber;
    private Long homeCode;
    private String cardNumber;
    private String district;
    private String street;
    private BuildingType buildingType;
}
