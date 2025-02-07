package com.zhoma.ecommerce.payment;


import com.zhoma.ecommerce.customer.CustomerResponse;
import com.zhoma.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}