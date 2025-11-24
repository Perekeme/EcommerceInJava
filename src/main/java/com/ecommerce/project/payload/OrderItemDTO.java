package com.ecommerce.project.payload;

import com.ecommerce.project.model.Order;
import com.ecommerce.project.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private  Long orderItemId;
    private ProductDTO productDTO;
    private Integer quantity;
    private  double discount;
    private  double orderedProductPrice;
}
