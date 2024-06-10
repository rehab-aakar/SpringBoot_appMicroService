package com.address.addressemicroservice.service.impl;

import com.address.addressemicroservice.dto.AddressDto;
import com.address.addressemicroservice.entity.Address;
import com.address.addressemicroservice.mapper.AddressMapper;
import com.address.addressemicroservice.repository.AddressRepository;
import com.address.addressemicroservice.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public Address AddAddress(AddressDto addressDto) {
        Address address = AddressMapper.fromAddressDtoToAddress(addressDto);
        return addressRepository.save(address);
    }

    @Override
    public Address AddAddressInternalVersion(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findAddressById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public boolean deleteAddressById(int id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            try{
                addressRepository.delete(address.get());
                return true;
            }catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean updateAddress(int id, AddressDto addressDto) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            Address addressToUpdate = AddressMapper.fromAddressDtoToAddress(addressDto);
            addressToUpdate.setId(id);
            try{
                addressRepository.save(addressToUpdate);
                return true;
            }catch (Exception e) {
                return false;
            }

        }
        return false;
    }
}
