package com.e_commerce.e_commerce.dto.response;



public record AddressResponse(long id,String name, String surname,String phone,
                              String city, String district, String neighborhood, String address) {
}
