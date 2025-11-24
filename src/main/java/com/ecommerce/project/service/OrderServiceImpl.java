package com.ecommerce.project.service;

import com.ecommerce.project.payload.OrderDTO;
import com.ecommerce.project.payload.OrderRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{


    @Override
    @Transactional
    public OrderDTO placeOrder(String emailId, OrderRequestDTO orderRequestDTO, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage) {
        // Getting User Cart
        // Create new order with payment info
//        get items from the cart into the order items
//        updatea  product sotck
//        clear the cart
//                send back the order summary
        return null;

    }
}
