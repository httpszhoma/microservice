package com.zhoma.ecommerce.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    public Integer createOrder(OrderRequest request) {
   // check customer
   // purchase the product to ms
   // persist order
   // persist order lines
   // start online payment process
   // send the order confirmation -- > notification-ms (kafka)
return 1;

    }

    public List<OrderResponse> findAllOrders() {
        return new ArrayList<>();
    }

    public OrderResponse findById(Integer orderId) {
        return new OrderResponse(1,"sds",new BigDecimal(12),PaymentMethod.CREDIT_CARD,"1");
    }
}
