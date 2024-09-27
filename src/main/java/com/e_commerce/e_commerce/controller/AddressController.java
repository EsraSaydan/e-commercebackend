package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.converter.Converter;
import com.e_commerce.e_commerce.dto.response.AddressResponse;
import com.e_commerce.e_commerce.entity.Address;
import com.e_commerce.e_commerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable long id){
        return Converter.findAddress(addressService.findAddressByID(id));
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(@RequestBody Address address, @PathVariable long id){
        return null;

    }

}