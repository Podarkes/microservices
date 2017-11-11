package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderPaidEvent implements BaseEvent{
	private int orderId;
	
	private String cardNumber;
	
	private double amount;
}
