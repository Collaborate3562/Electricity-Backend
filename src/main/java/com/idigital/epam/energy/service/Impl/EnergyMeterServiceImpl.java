package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.payload.EnergyMeterRequest;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.payload.Response;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.service.EnergyMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class EnergyMeterServiceImpl implements EnergyMeterService {

    @Autowired
    EnergyMeterRepository energyMeter;

    @Autowired
    HomeRepository homeRepository;

    @Override
    public List<EnergyMeter> getAll() {

        return energyMeter.findAll();
    }

    @Override
    public EnergyMeter getById(Long id) {

        return Optional.of(energyMeter.findById(id)).filter(Optional::isPresent).get().get();
//        return energyMeter.getById(id);
    }

    @Override
    public Response create(EnergyMeterRequest data) throws Exception {

            Home home = homeRepository.findById(data.getHomeId()).get();
            EnergyMeter energyMeter1r=new EnergyMeter();
            energyMeter1r.setEnergyConsumption(data.getEnergyConsumption());
            energyMeter1r.setHome(home);


            energyMeter.save(energyMeter1r);
            return new Response("Saved",Boolean.TRUE);
    }

    @Override
    public List<EnergyResponse> getAllEnergyMeter() {

       return energyMeter.getAllEnergy();
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void EnergyMeterCalculation() {
        List<EnergyMeter> energy = getAll();
        energy.forEach(e ->{
            if(e.getPreviousReading() != null){
                try {
                    e.setPreviousReading(e.getPreviousReading() + e.getEnergyConsumption());
                    update(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                try {
                    e.setPreviousReading(e.getEnergyConsumption());
                    update(e);
                } catch (Exception ex) {
                    ex.printStackTrace();


                }
            }
        });

    }

    @Override
    public EnergyMeter getByHomeId(Long homeId) {
        return energyMeter.findByHomeId(homeId);
    }


    @Override
    public EnergyMeter update(EnergyMeter data) throws Exception {
        if (data.getId() != null){
            return energyMeter.save(data);
        }
        throw new Exception("id shouldn't be null");
    }

    @Override
    public void delete(EnergyMeter data) {
        energyMeter.deleteById(data.getId());
    }

}
