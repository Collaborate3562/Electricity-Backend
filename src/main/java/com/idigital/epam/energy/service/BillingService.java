package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.payload.BillingRequest;

import java.util.List;

public interface BillingService  extends CommonService<Billing>{

    public Billing create(Billing billingRequest) throws Exception;
    public Billing update(Billing billing)  throws Exception;
    public void deleteById(Long id)  throws Exception;


    List<Billing> findByHomeId(Long homeId) throws Exception;
}
