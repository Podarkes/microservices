package org.it.discovery.monolith.repository;

import java.util.List;

import org.it.discovery.monolith.domain.Order;

public interface OrderRepository {
	
	void save(Order order);

	Order findById(int orderId);
	
	List<Order> findOrders();
}
