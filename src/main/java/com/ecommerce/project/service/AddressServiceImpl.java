package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.model.*;
import com.ecommerce.project.payload.*;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    private String createAddress(){
//        Address userAddress = addressRepository.findAddressByEmail(authUtil.loggedInEmail());
//        if (userAddress != null){
//            return  userAddress;
//        }
//
//        Address address = new Address();
//
//        cart.setTotalPrice(0.00);
//        cart.setUser(authUtil.loggedInUser());
//
//        Cart newCart = cartRepository.save(cart);
//        return newCart;
//    }
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

//    @Override
//    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        // Validate if the category name is already present
//        if (categoryRepository.findByCategoryName(categoryDTO.getCategoryName()) != null) {
//            throw new APIException(
//                    "Category with the name '" + categoryDTO.getCategoryName() + "' already exists."
//            );
//        }
//        // Map DTO -> Entity
//        Category category = new Category();
//        category.setCategoryName(categoryDTO.getCategoryName());
//
//        //Save the new category and return as DTO
//        Category savedCategory = categoryRepository.save(category);
//        CategoryDTO savedCategoryDTO = new CategoryDTO(savedCategory.getCategoryId(), savedCategory.getCategoryName());
//        return savedCategoryDTO;
//    }
//    private Cart createCart(){
//        Cart userCart  = cartRepository.findCartByEmail(authUtil.loggedInEmail());
//        if (userCart != null){
//            return  userCart;
//        }
//
//        Cart cart = new Cart();
//        cart.setTotalPrice(0.00);
//        cart.setUser(authUtil.loggedInUser());
//
//        Cart newCart = cartRepository.save(cart);
//        return newCart;
//    }

    @Override
    public AddressResponse getAllAddresses(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        return null;
    }

    @Override
    public AddressDTO getAddress(String emailId, Long addressId) {
        return null;
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO, long addressId) {
        return null;
    }

    @Override
    public String deleteAddress(Long addressId) {
        return null;
    }

}
