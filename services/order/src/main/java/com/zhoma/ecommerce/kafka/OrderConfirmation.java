package com.zhoma.ecommerce.kafka;

import com.zhoma.ecommerce.customer.CustomerResponse;
import com.zhoma.ecommerce.order.PaymentMethod;
import com.zhoma.ecommerce.product.PurchaseRequest;
import com.zhoma.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
){
}
