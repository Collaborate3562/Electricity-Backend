package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.repository.AddressRepository;
import com.idigital.epam.energy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean deleteAddress(Long addressId) {
        try {
            addressRepository.deleteById(addressId);
            return Boolean.TRUE;
        }catch (Exception e){
            System.out.println(e);
        }
        return Boolean.FALSE;

    }

    @Override
    public boolean updateAddress(Address address, Long id) {
        try {
            Address newAddress = addressRepository.findById(id).get();
            newAddress.setStreet(address.getStreet());
            newAddress.setDistrict(address.getDistrict());
            newAddress.setHomeNumber(address.getHomeNumber());
            newAddress.setId(id);
            addressRepository.save(newAddress);
            return Boolean.TRUE;
        }catch (Exception e){
            System.out.println(e);
        }
        return Boolean.FALSE;
    }


    @Override
    public Address getAddress(Long id) {
        return addressRepository.findById(id).get();
    }


}
