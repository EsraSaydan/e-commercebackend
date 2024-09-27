package com.e_commerce.e_commerce.service;


import com.e_commerce.e_commerce.entity.Address;

import java.util.List;

public interface AddressService {
    Address findAddressByID(long id);
    List<Address> getAllAddress();
    String saveAddress(Address address);
}
