package org.it.discovery.monolith.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.it.discovery.monolith.domain.Customer;

public class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<Integer, Customer> customers = new HashMap<>();

	private int counter;

	public InMemoryCustomerRepository() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Sam Newman");
		customer.setEmail("sam.newman@gmail.com");
		customers.put(customer.getId(), customer);
	}

	@Override
	public Customer findById(int customerId) {
		return customers.get(customerId);
	}

	@Override
	public void save(Customer customer) {
		if (customer.getId() == 0) {
			customer.setId(++counter);
			customers.put(customer.getId(), customer);
		} else {
			customers.put(customer.getId(), customer);
		}
	}

	@Override
	public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
	}

}
