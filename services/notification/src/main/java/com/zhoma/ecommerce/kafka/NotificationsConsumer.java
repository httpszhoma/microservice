package com.zhoma.ecommerce.kafka;


import com.zhoma.ecommerce.kafka.order.OrderConfirmation;
import com.zhoma.ecommerce.kafka.payment.PaymentConfirmation;
import com.zhoma.ecommerce.notification.Notification;
import com.zhoma.ecommerce.notification.NotificationRepository;
import com.zhoma.ecommerce.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationsConsumer {
    private final NotificationRepository repository;


    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info("Consuming the message from payment-topic Topic:: %s", paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()

        );

        var customerName = paymentConfirmation.customerFirstname()+ " "+ paymentConfirmation.customerLastname();




    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Consuming the message from order-topic Topic:: %s", orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()

        );

    }
}
