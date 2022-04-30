package com.idigital.epam.energy.controller;


import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.service.BillingService;
import com.idigital.epam.energy.payload.BillingRequest;
import com.idigital.epam.energy.service.EnergyMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/billing")
@CrossOrigin(origins = "*")
public class BillingController {
    @Autowired
    HomeRepository homeRepository;

    @Autowired
    EnergyMeterService energyMeterService;

    @Autowired
    BillingService billingService;

    @Autowired
    BillingRepository billingRepository;



    @GetMapping("/getAllBillings")
    public List<Billing> getAll() throws Exception {

        List<Billing> billings = billingService.getAll();
        //return new ResponseEntity(billings, HttpStatus.OK);
        return billings;
    }
    @GetMapping("/getBillingByHomeId/{id}")
    public List<Billing> getBillingByHomeId(@PathVariable("id") Long id)throws Exception{
        List<Billing> billings = billingService.findByHomeId(id);
        return billings;
    }

    @PostMapping("/save")
    public Billing createBilling(@RequestBody Billing billingRequest) throws Exception {
        return billingService.create(billingRequest);

    }


    @PutMapping("/update")
    public Billing updateBilling(@RequestBody Billing billing) throws Exception {
        return billingService.update(billing);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteBilling(@PathVariable("id") Long id) throws Exception {
         billingService.deleteById(id);

    }


    @GetMapping("/getBilling/{id}")
    public EnergyMeter getBillingBasedOnId(@PathVariable("id") Long id){
       // Billing billing = billingService.getById(id);

        EnergyMeter energyMeter = energyMeterService.getById(id);
//       billingRepository.findByEnergyMeter(energyMeter);

       // return new ResponseEntity(billingRepository.findByEnergyMeter(energyMeter), HttpStatus.OK);
        return energyMeter;
    }

    @GetMapping("/getOne/{id}")
    public Billing getById(@PathVariable("id") Long id){
        return billingService.getById(id);
    }



}
