package com.ecommerce.project.payload;

import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.OrderItem;
import com.ecommerce.project.model.Payment;
import com.ecommerce.project.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private List<OrderItemDTO> orderItems;
    private  Double totalAmount;
    private String email;
    private LocalDate orderDate;
    private String orderStatus;
    private PaymentDTO payment;
    private Long addressId;

}
