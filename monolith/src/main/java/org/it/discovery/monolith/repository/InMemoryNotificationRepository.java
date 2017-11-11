package org.it.discovery.monolith.repository;

import java.util.ArrayList;
import java.util.List;

import org.it.discovery.monolith.domain.Notification;

public class InMemoryNotificationRepository implements
NotificationRepository{
	private final List<Notification> notifications = 
			new ArrayList<>();

	@Override
	public void save(Notification notification) {
		notifications.add(notification);
	}

}
