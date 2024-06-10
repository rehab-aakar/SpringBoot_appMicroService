package com.address.addressemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public class AddressDto {
        private String street;
        private String city;
        private String country;
    }

