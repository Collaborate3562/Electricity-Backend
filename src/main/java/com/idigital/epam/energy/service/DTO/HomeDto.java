package com.idigital.epam.energy.service.DTO;


import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.enums.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class HomeDto {
    private Long homeCode;
    private Address address;

}
