package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderCompletedEvent {
	private int orderId;
}
