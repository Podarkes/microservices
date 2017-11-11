package it.discovery.microservice.order;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public int createOrder(int bookId, int number, int customerId) {
		return orderService.createOrder(bookId, number, customerId).getId();		
	}
	
	public void addBook(int orderId, int bookId, int number) {
		orderService.addBook(orderId, bookId, number);
	}
	
	public void completeOrder(int orderId) {
		orderService.complete(orderId);
	}
	
	public void deliver(int orderId) {
		orderService.deliver(orderId);			
	}
	
	public void cancel(int orderId) {
		orderService.cancel(orderId);
	}
	
	public List<Order> findOrders() {
		return orderService.findOrders();
	}
}
