package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.BillingService;
import com.idigital.epam.energy.payload.BillingRequest;
import com.idigital.epam.energy.service.DTO.RequestPayment;
import com.idigital.epam.energy.service.EnergyMeterService;
import com.idigital.epam.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    BillingRepository billingRepository;

    @Autowired
    EnergyMeterRepository energyMeterRepository;

    @Autowired
    EnergyMeterService energyMeterService;

    @Autowired
    HMACUtilsService hmACUtils;
    @Autowired
    UserService userService;

    @Override
    public List<Billing> getAll() {
        return billingRepository.findAll();
    }



    @Override
    public Billing getById(Long id) {

        return Optional.of(billingRepository.findById(id)).filter(Optional::isPresent).get().get();
    }

    @Override
    public Billing create(Billing billingRequest) throws Exception {

        Billing billing = new Billing();
//        billing.setPreviousReading(billingRequest.getPreviousReading());

        RequestPayment paymentRequest = new RequestPayment(billingRequest.getSum(), userService.getCurrentUser().getCardNumber());
        String some =hmACUtils.postRequestWithHmac("ENERGY", "payment", "http://54.236.248.165/api/v1/pay-notification", "energyKey", paymentRequest).getBody().toString();
        if(some != null){
            return billingRepository.save(billingRequest);
        }else{
            throw new Exception("We have some problem with connection");
        }


    }

    @Override
    public Billing update(Billing billing) throws Exception{

        if(billingRepository.findById(billing.getId()).isPresent()){
            billingRepository.save(billing);
            return billingRepository.getOne(billing.getId());
        }else{
            throw new Exception("Billing Not found for Id "+billing.getId());
        }

    }

      @Override
    public void deleteById(Long id) throws Exception {

          if(billingRepository.findById(id).isPresent()){
              billingRepository.deleteById(id);

          }else{
              throw new Exception("Billing Not found for Id "+id);
          }


    }

    @Override
    public void delete(Billing billing){

    }

    @Override
    public List<Billing> findByHomeId(Long homeId) throws Exception {
        EnergyResponse energyMeter = energyMeterRepository.getEnergyByHomeID(homeId);
        List<Billing> billingData = billingRepository.findByEnergyMeterId(energyMeter.getId());
        return billingData;
    }
}
