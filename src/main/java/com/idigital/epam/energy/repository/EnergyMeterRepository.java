package com.idigital.epam.energy.repository;

import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.payload.EnergyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnergyMeterRepository extends JpaRepository<EnergyMeter, Long> {

    @Query("select new com.idigital.epam.energy.payload.EnergyResponse(" +
            "em.id, em.energyConsumption,em.home.homeCode,em.home.buildingType,em.home.user.cardNumber,em.home.user.firstName) from EnergyMeter em")
    List<EnergyResponse> getAllEnergy();

    @Query("select new com.idigital.epam.energy.payload.EnergyResponse(" +
            "em.id, em.energyConsumption,em.home.homeCode,em.home.buildingType,em.home.user.cardNumber,em.home.user.firstName) from EnergyMeter em where em.home.id=?1")
    EnergyResponse getEnergyByHomeID(Long homeId);

    EnergyMeter findByHomeId(Long homeId);
}
