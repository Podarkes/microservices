package org.it.discovery.monolith.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import it.discovery.microservice.MicroserviceApplication;
import it.discovery.microservice.order.OrderService;

@SpringJUnitWebConfig(classes=MicroserviceApplication.class)
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	void findOrders_RepositoryEmpty_NothingReturned() {
		assertTrue(orderService.findOrders().isEmpty());
	}

}
