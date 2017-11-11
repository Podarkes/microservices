package it.discovery.microservice.event;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class OrderDeliveredEvent {
	private int orderId;

	private LocalDateTime deliveryDate;
}
