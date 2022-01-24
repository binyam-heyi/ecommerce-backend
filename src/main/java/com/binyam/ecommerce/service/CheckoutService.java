package com.binyam.ecommerce.service;


import com.binyam.ecommerce.dto.Purchase;
import com.binyam.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
