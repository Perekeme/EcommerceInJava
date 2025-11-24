package com.ecommerce.project.service;

import com.ecommerce.project.payload.OrderDTO;
import com.ecommerce.project.payload.OrderRequestDTO;
import jakarta.transaction.Transactional;

public interface OrderService {
    @Transactional
    OrderDTO placeOrder(String emailId, OrderRequestDTO orderRequestDTO,
                        String paymentMethod, String pgName, String pgPaymentId,
                        String pgStatus, String pgResponseMessage);
}
