package it.discovery.microservice.event;

import lombok.Value;

@Value
public class PaymentSuccessEvent implements BaseEvent {
	
	private int orderId;

}
