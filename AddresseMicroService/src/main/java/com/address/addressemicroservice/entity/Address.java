package com.address.addressemicroservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
    public class Address {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String street;
        private String city;
        private String country;
    }

