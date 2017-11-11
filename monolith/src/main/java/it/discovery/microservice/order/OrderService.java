package it.discovery.microservice.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import it.discovery.microservice.book.BookRepository;
import it.discovery.microservice.customer.CustomerRepository;
import it.discovery.microservice.delivery.DeliveryService;
import it.discovery.microservice.event.BaseEvent;
import it.discovery.microservice.event.OrderCompletedEvent;
import it.discovery.microservice.event.PaymentSuccessEvent;
import it.discovery.microservice.event.bus.EventBus;
import it.discovery.microservice.event.bus.EventListener;
import it.discovery.microservice.notification.Notification;
import it.discovery.microservice.notification.NotificationService;

@Service
public class OrderService implements EventListener {

	private final OrderRepository orderRepository;

	private BookRepository bookRepository;

	private DeliveryService deliveryService;

	private CustomerRepository customerRepository;

	private final NotificationService notificationService;

	//private EventBus eventBus = EventBus.getInstance();
	private EventBus eventBus;

	public OrderService(OrderRepository orderRepository,
						NotificationService notificationService,
						EventBus eventBus) {
		this.orderRepository = orderRepository;
		this.eventBus = eventBus;
		eventBus.subscribe(this);
		this.notificationService = notificationService;
	}

	public void deliver(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			deliveryService.deliver(order);
		}
	}

	public void complete(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			eventBus.sendEvent(
					new OrderCompletedEvent(order.getId(), order.getCustomer().getCardNumber(), order.getAmount()));
		}
	}

	public void cancel(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.setCancelled(true);
			orderRepository.save(order);

			Notification notification = new Notification();
			notification.setEmail(order.getCustomer().getEmail());
			notification.setRecipient(order.getCustomer().getName());
			notification.setTitle("Order " + order.getId() + " is canceled");
			notification.setText("Hi/n. Your order has been canceled");

			notificationService.sendNotification(notification);
		}
	}

	public Order createOrder(int bookId, int number, int customerId) {
		Order order = new Order();
		order.addItem(new OrderItem(bookRepository.findBookById(bookId), number));
		order.setOrderDate(LocalDateTime.now());
		order.setCustomer(customerRepository.findById(customerId));

		return order;
	}

	public void addBook(int orderId, int bookId, int number) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.addItem(new OrderItem(bookRepository.findBookById(bookId), number));
			orderRepository.save(order);
		}
	}

	public void save(Order order) {
		orderRepository.save(order);
	}

	public List<Order> findOrders() {
		return orderRepository.findOrders();
	}

	@org.springframework.context.event.EventListener
	public void pay(PaymentSuccessEvent event) {
		Order order = orderRepository.findById(event.getOrderId());
		order.setPaid(true);
		orderRepository.save(order);

		Notification notification = new Notification();
		notification.setEmail(order.getCustomer().getEmail());
		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + order.getId() + " is completed");
		notification.setText("Hi/n. Your order has been completed");

		notificationService.sendNotification(notification);
	}
	@Override
	public void accept(BaseEvent event) {
		if (event instanceof PaymentSuccessEvent) {
			PaymentSuccessEvent paymentEvent = (PaymentSuccessEvent) event;

			pay(paymentEvent);
		}

	}

}
