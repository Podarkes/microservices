package org.it.discovery.monolith.service;

import org.it.discovery.monolith.domain.Order;
import org.it.discovery.monolith.repository.OrderRepository;

public class PaymentService {
	private OrderRepository orderRepository;
	
	public void pay(Order order) {
		System.out.println("Charging " + order.getAmount() + " from credit card " + order.getCustomer().getCardNumber()); 
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		order.setPayed(true);
		orderRepository.save(order);
		System.out.println("Charging completed");
	}

}
