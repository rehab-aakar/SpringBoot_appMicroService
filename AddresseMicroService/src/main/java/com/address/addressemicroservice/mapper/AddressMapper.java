package com.address.addressemicroservice.mapper;

import com.address.addressemicroservice.dto.AddressDto;
import com.address.addressemicroservice.entity.Address;

public class AddressMapper {
    public static Address fromAddressDtoToAddress(AddressDto addressDto){
        /*Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return address;*/
        // another way to copy properties using builder pattern
        return new Address().builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();
    }
}
