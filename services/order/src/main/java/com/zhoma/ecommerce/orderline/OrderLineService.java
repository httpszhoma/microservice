package com.zhoma.ecommerce.orderline;

import com.zhoma.ecommerce.order.OrderMapper;
import com.zhoma.ecommerce.order.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;
    public void saveOrderLine(OrderLineRequest orderRequest) {
        OrderLine order  = mapper.toOrderLine(orderRequest);



    }
}
