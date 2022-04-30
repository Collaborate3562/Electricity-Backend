package com.idigital.epam.energy.controller;


import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.payload.EnergyMeterRequest;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.payload.Response;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.EnergyMeterService;
import com.idigital.epam.energy.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/energymeter")
//@CrossOrigin(maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnergyMeterController {

    @Autowired
    EnergyMeterService energyMeterService;

    @Autowired
    HomeService homeService;

    @Autowired
    EnergyMeterRepository energyMeterRepository;

    @PostMapping("/save")
    public ResponseEntity<Response> createEnergyMeter(@RequestBody EnergyMeterRequest energyMeterRequest) throws Exception {
        return ResponseEntity.ok(energyMeterService.create(energyMeterRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnergyResponse>> getAll(){
        List<EnergyResponse> energyMeters = energyMeterService.getAllEnergyMeter();
        return new ResponseEntity<List<EnergyResponse>>(energyMeters, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EnergyMeter> getEnergyMeterById(@PathVariable Long id){
        EnergyMeter energyMeter = energyMeterService.getById(id);
        return ResponseEntity.ok(energyMeter);
    }
    @GetMapping("/getByHomeId/{homeId}")
    public ResponseEntity<?> getByHomeId(@PathVariable Long homeId){
        return ResponseEntity.ok(energyMeterService.getByHomeId(homeId));
    }

    @GetMapping("/findByHomeId/{homeId}")
    public ResponseEntity<?> findByHomeId(@PathVariable Long homeId){
        return ResponseEntity.ok(energyMeterRepository.findByHomeId(homeId));
    }



}


