package com.ecommerce.project.service;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.payload.AddressResponse;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    AddressResponse getAllAddresses(Integer pageNumber, Integer pageSize, String sortBy , String sortOrder);

    AddressDTO getAddressById(Long addressId);

    AddressDTO getAddress(String emailId, Long addressId);

    AddressDTO updateAddress(AddressDTO addressDTO, long addressId);

    String deleteAddress(Long addressId);
}
