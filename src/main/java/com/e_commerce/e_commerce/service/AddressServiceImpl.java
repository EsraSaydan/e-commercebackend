package com.e_commerce.e_commerce.service;


import com.e_commerce.e_commerce.entity.Address;

import com.e_commerce.e_commerce.exception.CommerceException;
import com.e_commerce.e_commerce.repository.AddressRepository;

import com.e_commerce.e_commerce.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findAddressByID(long id) {
        Optional<Address> addressOptional=addressRepository.findById(id);
        if(addressOptional.isPresent()){
            return addressOptional.get();
        }
        throw new CommerceException(EntityValidator.IS_NOT_PRESENT_ADDRESS, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public String saveAddress(Address address) {
        EntityValidator.checkEmptyOrNull(address.getName(),"name");
        EntityValidator.checkEmptyOrNull(address.getCity(),"city");
        EntityValidator.checkEmptyOrNull(address.getDistrict(),"district");
        EntityValidator.checkEmptyOrNull(address.getTitle(),"title");
        EntityValidator.checkEmptyOrNull(address.getNeighborhood(),"neighborhood");
        EntityValidator.checkEmptyOrNull(address.getSurname(),"surname");
        EntityValidator.checkEmptyOrNull(address.getPhone(),"phone");
        addressRepository.save(address);
        return "address has been added successfully";
    }
}