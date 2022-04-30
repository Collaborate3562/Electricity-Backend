package com.idigital.epam.energy.repository;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.payload.EnergyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    @Query("select b from Billing b where b.energyMeter.id = ?1")
    List<Billing> findByEnergyMeterId(Long energyMeterId);
}
