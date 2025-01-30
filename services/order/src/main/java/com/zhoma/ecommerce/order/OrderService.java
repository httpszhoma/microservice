package com.zhoma.ecommerce.order;


import com.zhoma.ecommerce.customer.CustomerClient;
import com.zhoma.ecommerce.exception.BusinessException;
import com.zhoma.ecommerce.kafka.OrderConfirmation;
import com.zhoma.ecommerce.kafka.OrderProducer;
import com.zhoma.ecommerce.orderline.OrderLineRequest;
import com.zhoma.ecommerce.orderline.OrderLineService;
import com.zhoma.ecommerce.product.ProductClient;
import com.zhoma.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));
        var purchaseProducts = productClient.purchaseProducts(request.products());

        var order = orderRepository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }


        // TODO : start online payment process


        // send the order confirmation -- > notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                ));

        return order.getId();

    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new BusinessException(String.format("No order found with the provided ID: %d", orderId)));

    }
}
