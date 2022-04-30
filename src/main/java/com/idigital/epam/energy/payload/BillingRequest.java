package com.idigital.epam.energy.payload;

import com.idigital.epam.energy.entity.EnergyMeter;
import lombok.Data;

@Data
public class BillingRequest {

    private Integer energyConsumption;
    private Integer amountEnergyConsumption;
    private Integer Sum;

    private EnergyMeter energyMeterId;




}
//
//    private Integer previousReading;
//    private Integer currentReading;
//    private Integer amountEnergyConsumption;
//    private Integer Sum;
//
//    private EnergyMeter energyMeterId;