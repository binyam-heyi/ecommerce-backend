package com.binyam.ecommerce.dto;

import com.binyam.ecommerce.entity.Address;
import com.binyam.ecommerce.entity.Customer;
import com.binyam.ecommerce.entity.Order;
import com.binyam.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
