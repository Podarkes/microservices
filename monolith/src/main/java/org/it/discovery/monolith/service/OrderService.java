package org.it.discovery.monolith.service;

import java.time.LocalDateTime;
import java.util.List;

import org.it.discovery.monolith.domain.Notification;
import org.it.discovery.monolith.domain.Order;
import org.it.discovery.monolith.domain.OrderItem;
import org.it.discovery.monolith.repository.BookRepository;
import org.it.discovery.monolith.repository.CustomerRepository;
import org.it.discovery.monolith.repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;

	private BookRepository bookRepository;

	private DeliveryService deliveryService;
	
	private CustomerRepository customerRepository;
	
	private NotificationService notificationService;
	
	private PaymentService paymentService;

	public void deliver(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			deliveryService.deliver(order);
		}
	}

	public void complete(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {			
			paymentService.pay(order);
			
			orderRepository.save(order);
			
			Notification notification = new Notification();
			notification.setEmail(order.getCustomer().getEmail());
			notification.setRecipient(order.getCustomer().getName());
			notification.setTitle("Order " + order.getId() + " is completed");
			notification.setText("Hi/n. Your order has been completed");
			
			notificationService.sendNotification(notification);
		}
	}

	public void cancel(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.setCancelled(true);
			orderRepository.save(order);
			
			Notification notification = new Notification();
			notification.setEmail(order.getCustomer().getEmail());
			notification.setRecipient(order.getCustomer().getName());
			notification.setTitle("Order " + order.getId() + " is canceled");
			notification.setText("Hi/n. Your order has been canceled");
			
			notificationService.sendNotification(notification);
		}
	}

	public Order createOrder(int bookId, int number, int customerId) {
		Order order = new Order();
		order.addItem(new OrderItem(bookRepository.findBookById(bookId), number));
		order.setOrderDate(LocalDateTime.now());
		order.setCustomer(customerRepository.findById(customerId));

		return order;
	}

	public void addBook(int orderId, int bookId, int number) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.addItem(new OrderItem(bookRepository.findBookById(bookId), number));
			orderRepository.save(order);
		}
	}
	
	public List<Order> findOrders() {
		return orderRepository.findOrders();
	}

}
