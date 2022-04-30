package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Address;

public interface AddressService {
    Address createAddress(Address address);
    boolean deleteAddress(Long addressId);
    boolean updateAddress(Address address, Long id);
    Address getAddress(Long id);

}
