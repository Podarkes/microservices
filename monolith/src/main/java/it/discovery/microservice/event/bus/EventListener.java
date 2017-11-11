package it.discovery.microservice.event.bus;

import it.discovery.microservice.event.BaseEvent;

public interface EventListener {
	void accept(BaseEvent event);
}
