package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.*;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.service.ProductService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ecommerce.project.config.AppConstants.SORT_CATEGORIES_BY;
import static com.ecommerce.project.config.AppConstants.SORT_DIR;


@RestController
@RequestMapping("/api")
public class AddressController {
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    AuthUtil authUtil;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {

        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO , user);
        return new ResponseEntity<AddressDTO>(savedAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>>  getAddresses (){
        List<AddressDTO> addressList = addressService.getAddresses();

        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

     @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO>  getAddressById ( @PathVariable Long addressId){

        AddressDTO addressDTO = addressService.getAddressById(addressId);

         return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses (){
       User user = authUtil.loggedInUser();
       List<AddressDTO> addressList = addressService.getUserAddresses(user);
       return new ResponseEntity<> (addressList, HttpStatus.OK);

    }

    @PutMapping ("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress ( @Valid @PathVariable long addressId,  @RequestBody AddressDTO addressDTO ) {
        AddressDTO updatedAddressDTO = addressService.updateAddress( addressDTO, addressId );
        return  new ResponseEntity<> ( updatedAddressDTO, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {

        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }

}


