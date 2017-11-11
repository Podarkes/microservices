package org.it.discovery.monolith.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Notification {
	private String recipient;

	private String email;

	private String title;

	private String text;
	
	private LocalDateTime created = LocalDateTime.now();
}
