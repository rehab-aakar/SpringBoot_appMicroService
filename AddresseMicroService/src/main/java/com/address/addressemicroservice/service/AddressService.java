package com.address.addressemicroservice.service;

import com.address.addressemicroservice.dto.AddressDto;
import com.address.addressemicroservice.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    public Address AddAddress(AddressDto address);
    public Address AddAddressInternalVersion(Address address);
    public List<Address> findAllAddresses();
    public Optional<Address> findAddressById(int id);
    public boolean deleteAddressById(int id);
    public boolean updateAddress(int id,AddressDto address);
}