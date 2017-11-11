package it.discovery.microservice.event.bus;

import it.discovery.microservice.event.BaseEvent;

public interface EventBus {
	
	public static EventBus getInstance() {
		return SyncEventBus.INSTANCE;
	}

	void sendEvent(BaseEvent event);
	
	void subscribe(EventListener listener);

}
