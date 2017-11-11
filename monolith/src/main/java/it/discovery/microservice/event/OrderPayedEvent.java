package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderPayedEvent {
	private int orderId;
	
	private String cardNumber;
	
	private double amount;
}
