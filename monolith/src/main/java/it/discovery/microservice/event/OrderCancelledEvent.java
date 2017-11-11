package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderCancelledEvent {
	private int orderId;

}
