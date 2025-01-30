package com.zhoma.ecommerce.orderline;


public record OrderLineResponse(
        Integer id,
        double quantity
) { }