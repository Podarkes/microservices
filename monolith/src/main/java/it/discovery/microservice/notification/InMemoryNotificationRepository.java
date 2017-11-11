package it.discovery.microservice.notification;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotificationRepository implements
NotificationRepository{
	private final List<Notification> notifications = 
			new ArrayList<>();

	@Override
	public void save(Notification notification) {
		notifications.add(notification);
	}

}
