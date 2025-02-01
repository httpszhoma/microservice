package com.zhoma.ecommerce.orderline;

import com.zhoma.ecommerce.order.OrderMapper;
import com.zhoma.ecommerce.order.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public void saveOrderLine(OrderLineRequest orderRequest) {
        OrderLine order = mapper.toOrderLine(orderRequest);

    }
        public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
            return repository.findAllByOrderId(orderId)
                    .stream()
                    .map(mapper::toOrderLineResponse)
                    .collect(Collectors.toList());
        }
    }