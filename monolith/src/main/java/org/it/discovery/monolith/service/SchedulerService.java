package org.it.discovery.monolith.service;

import org.it.discovery.monolith.domain.Notification;
import org.it.discovery.monolith.repository.OrderRepository;

public class SchedulerService {
	private OrderRepository orderRepository;
	
	private NotificationService notificationService;
	
	public void run() {
		while(true) {
			try {
				orderRepository.findOrders().stream().filter(order -> !order.isPayed())
					.forEach(order -> {
						Notification notification = new Notification();
						notification.setEmail(order.getCustomer().getEmail());
						notification.setRecipient(order.getCustomer().getName());
						notification.setTitle("Don't forget to pay order " + order.getId());
						notification.setText("Please, pay your outstaning order " + order.getId());
						
						notificationService.sendNotification(notification);
					});				
				//Activate each 30 seconds
				Thread.sleep(30 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
