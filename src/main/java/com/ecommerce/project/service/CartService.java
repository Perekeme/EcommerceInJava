package com.ecommerce.project.service;

import com.ecommerce.project.payload.CartDTO;

import java.util.List;

public interface CartService {

    public CartDTO addProductToCart(Long productId, Integer quantity);

   public List<CartDTO> getAllCarts();
}
