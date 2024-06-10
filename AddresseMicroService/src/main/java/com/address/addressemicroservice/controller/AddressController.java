package com.address.addressemicroservice.controller;


import com.address.addressemicroservice.dto.AddressDto;
import com.address.addressemicroservice.entity.Address;
import com.address.addressemicroservice.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<Object> addAddress(@RequestBody AddressDto addressDto) {
        log.info("Add address");
        Address address = addressService.AddAddress(addressDto);
        if(address != null) {
            log.info("Address added successfully");
            return new ResponseEntity<>(address, HttpStatus.CREATED);
        }
        log.error("Address not added");
        return new ResponseEntity<>("Address not added",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddresses() {
        log.info("Get all addresses");
        List<Address> addressList = addressService.findAllAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable int id) {
        log.info("Get address by id");
        Optional<Address> address = addressService.findAddressById(id);

        return address.<ResponseEntity<Object>>map(
                        value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND)
                );
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable int id) {
        log.info("Delete address by id");
        boolean isDeleted = addressService.deleteAddressById(id);
        String responseMessage = isDeleted ? "Address is deleted" : "Address not found";
        HttpStatus status = isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        log.info(responseMessage);
        return new ResponseEntity<>(responseMessage, status);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Object> updateAddressById(@PathVariable int id, @RequestBody AddressDto addressDto) {
        boolean isUpdated = addressService.updateAddress(id,addressDto);
        log.info("Update address by id");
        Object responseMessage = null;
        if(isUpdated) {
            log.info("Address updated successfully");
            Map<String,Object> map = new HashMap<>();
            map.put("Is updated",true);
            map.put("data",addressDto);
            responseMessage = map;
        }else{
            log.error("Address not updated");
            responseMessage = "Address not found";
        }
        HttpStatus status = isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseMessage, status);
    }

}