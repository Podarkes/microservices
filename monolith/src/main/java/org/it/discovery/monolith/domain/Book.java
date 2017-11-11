package org.it.discovery.monolith.domain;

import lombok.Data;

@Data
public class Book {
	private int id;
	
	private String name;
	
	private int year;
	
	private int pages;
	
	private double price;
	
	private Person author;
}
