package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.payload.EnergyMeterRequest;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.payload.Response;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface EnergyMeterService extends CommonService<EnergyMeter> {

    public Response create(EnergyMeterRequest data) throws Exception;

    List<EnergyResponse> getAllEnergyMeter();

    @Scheduled(fixedRate = 5000)
    void EnergyMeterCalculation();

    EnergyMeter getByHomeId(Long homeId);
}
