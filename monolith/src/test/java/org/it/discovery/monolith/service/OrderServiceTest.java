package org.it.discovery.monolith.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import it.discovery.microservice.book.Book;
import it.discovery.microservice.customer.Customer;
import it.discovery.microservice.order.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import it.discovery.microservice.MicroserviceApplication;
import it.discovery.microservice.event.PaymentSuccessEvent;
import it.discovery.microservice.event.bus.EventBus;
import it.discovery.microservice.order.Order;
import it.discovery.microservice.order.OrderService;

@SpringJUnitWebConfig(classes = MicroserviceApplication.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ApplicationEventMulticaster multicaster;

    @Test
    void findOrders_RepositoryEmpty_NothingReturned() {
        assertTrue(orderService.findOrders().isEmpty());
    }

    @Test
    void complete_OrderIsValid_PaymentSuccessfull() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        multicaster.addApplicationListener(event ->
        {
            if (event instanceof PaymentSuccessEvent) {
                latch.countDown();
                assertTrue(true);
            }
        });
//        EventBus.getInstance().subscribe(event -> {
//            if (event instanceof PaymentSuccessEvent) {
//                latch.countDown();
//                assertTrue(true);
//            }
//        });

        Order order = new Order();
        OrderItem item = new OrderItem(new Book(), 1);
        order.addItem(item);
        order.setCustomer(new Customer());
        orderService.save(order);
        orderService.complete(order.getId());
        latch.await(1, TimeUnit.SECONDS);
        assertEquals(latch.getCount(), 0);
    }


}
