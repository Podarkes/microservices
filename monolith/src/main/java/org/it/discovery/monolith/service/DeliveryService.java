package org.it.discovery.monolith.service;

import java.time.LocalDateTime;

import org.it.discovery.monolith.domain.Notification;
import org.it.discovery.monolith.domain.Order;
import org.it.discovery.monolith.repository.OrderRepository;

public class DeliveryService {
	private OrderRepository orderRepository;
	
	private NotificationService notificationService;
	
	public void deliver(Order order) {
		order.setDelivered(true);
		order.setDeliveryDate(LocalDateTime.now());
		
		orderRepository.save(order);	
		
		Notification notification = new Notification();
		notification.setEmail(order.getCustomer().getEmail());
		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + order.getId() + " is delivered");
		notification.setText("Hi/n. Your order has been delivered");
		
		notificationService.sendNotification(notification);

		System.out.println("Order delivered!");
	}

}
