package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    AuthUtil authUtil;
    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
       Address address = modelMapper.map(addressDTO, Address.class);

       List<Address> addressList = user.getAddresses();
       addressList.add(address);
       user.setAddresses(addressList);

       address.setUser(user);
       Address savedAddress = addressRepository.save(address);

       return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
         List<AddressDTO> addressDTOs = addresses.stream()
                .map( address -> modelMapper.map(address,AddressDTO.class))
                .collect(Collectors.toList());
        return addressDTOs;
    }


    @Override
    public AddressDTO getAddressById(Long addressId) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow( () -> new ResourceNotFoundException( "Address", "Address Id", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO, long addressId) {

        Long userId = authUtil.loggedInUserId();
        Address address = addressRepository.findAddressByUserId(userId, addressId);

        // Perform Validations
        if (address == null) {
            throw new ResourceNotFoundException("Address","Address Id", addressId);
        }
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setCountry(addressDTO.getCountry());
        address.setPinCode(addressDTO.getPinCode());
        address.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(address);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }
//    @Override
//    public AddressDTO updateAddress(AddressDTO addressDTO, long addressId) {
//
//        Address address = addressRepository.findById(addressId)
//                .orElseThrow( () -> new ResourceNotFoundException( "Address", "Address Id", addressId));
//
//
//        address.setCity(addressDTO.getCity());
//        address.setState(addressDTO.getState());
//        address.setStreet(addressDTO.getStreet());
//        address.setCountry(addressDTO.getCountry());
//        address.setPinCode(addressDTO.getPinCode());
//        address.setBuildingName(addressDTO.getBuildingName());
//
//        Address updatedAddress = addressRepository.save(address);
//
//        User user = address.getUser();
//        user.getAddresses().removeIf(addresss -> address.getAddressId().equals(addressId));
//        user.getAddresses().add(updatedAddress);
//        userRepository.save(user);
//
//        return modelMapper.map(updatedAddress, AddressDTO.class);
//    }

    @Override
    public String deleteAddress(Long addressId) {

        Long userId = authUtil.loggedInUserId();
        Address address = addressRepository.findAddressByUserId(userId, addressId);

        // Perform Validations
        if (address == null) {
            throw new ResourceNotFoundException("Address","Address Id", addressId);
        }

        addressRepository.delete(address);


        return "Address deleted successfully with address Id "+ addressId;

    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {

        List<Address> addresses = user.getAddresses();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map( address -> modelMapper.map(address,AddressDTO.class))
                .collect(Collectors.toList());
        return addressDTOs;


    }

}
