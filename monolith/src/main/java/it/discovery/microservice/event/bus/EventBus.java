package it.discovery.microservice.event.bus;

import it.discovery.microservice.event.BaseEvent;

public interface EventBus {
	
	void sendEvent(BaseEvent event);
	
	void subscribe(EventListener listener);

}
