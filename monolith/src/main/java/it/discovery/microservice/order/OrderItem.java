package it.discovery.microservice.order;

import it.discovery.microservice.book.Book;
import lombok.Data;

@Data
public class OrderItem {
	private final Book book;
	
	private final int number;
}
