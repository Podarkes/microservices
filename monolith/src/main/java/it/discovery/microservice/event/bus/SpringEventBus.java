package it.discovery.microservice.event.bus;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import it.discovery.microservice.event.BaseEvent;

@Component
public class SpringEventBus implements EventBus{
	
	private final ApplicationEventPublisher
	publisher;
	
	public SpringEventBus(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void sendEvent(BaseEvent event) {
		publisher.publishEvent(event);
	}

	@Override
	public void subscribe(EventListener listener) {
		// TODO Auto-generated method stub
		
	}

}
